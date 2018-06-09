package com.openyogaland.denis.architecturesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Нужно написать андроид приложение с двумя страницами:
 *
 * 1) Прокручиваемый список элементов загружаемых с любого открытого API (например, gitHub).
 * Предусмотреть вариант, что список может быть очень длинным (скажем до 1000 позиций) и его нужно
 * будет загружать частями
 *
 * 2) При нажатии на элемент списка открывается деталка в которой показаны более подробные данные
 * по элементу списка
 *
 * 3) Архитектура приложения должна позволять его удобное мастштабирование: добавление оффлайн
 * версии, фильтров списка, создание раздела "Избранное", итд
 *
 *  Можно использовать любые библиотеки, кроме тех которые уже содержат решение задачи.
 */
public class MainActivity extends AppCompatActivity implements OnDetailsRequestedListener,
                                                               OnLoadMoreItemsListener
{
  // constants
  private static final String       RANGE_TEMPLATE = "A%1$d:B%2$d";
  private static final int          FIRST_ELEMENT  = 1;
  private static final int          LOAD_ELEMENTS  = 50;
  
  // fields
  private ArrayList<YogaInstructor>      instructors      = new ArrayList<>();
  private OnYogaInstructorScrollListener onScrollListener = new OnYogaInstructorScrollListener();
  private InstructorAdapter              instructorAdapter;
  private LinearLayoutManager            layoutManager;
  private int                            loadStartItemBound;
  private int                            loadEndItemBound;
  private boolean                        loading          = false;
  
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    // find RecyclerView
    RecyclerView  instructorRecyclerView  = findViewById(R.id.instructorRecyclerView);
    
    // set LayoutManager to RecyclerView
    layoutManager = new LinearLayoutManager(this);
    instructorRecyclerView.setLayoutManager(layoutManager);
  
    // set Adapter to RecyclerView
    instructorAdapter = new InstructorAdapter(instructors);
    instructorRecyclerView.setAdapter(instructorAdapter);
  
    // add OnScrollListener to RecyclerView (there may be more than one)
    instructorRecyclerView.addOnScrollListener(onScrollListener);
    // set OnLoadMoreItemsListener
    onScrollListener.setOnLoadMoreItemsListener(this);
    
    // set custom listener to Adapter
    instructorAdapter.setOnDetailsRequestedListener(this);
    instructorAdapter.setOnLoadMoreItemsListener(this);
    
    // check internet connection and call Api
    if(ArchitectureSample.hasConnection(this))
    {
      Toast.makeText(this, "Fetching data from Google Sheets Api", Toast.LENGTH_LONG).show();
      
      // load 50 elements then called for the first time
      loadStartItemBound = FIRST_ELEMENT;
      loadEndItemBound   = LOAD_ELEMENTS;
      loadItems(loadStartItemBound, loadEndItemBound);
    }
    else
    {
      Toast.makeText(this, "Check the internet connection", Toast.LENGTH_LONG).show();
    }
  }
  
  /**
   * start second activity with details
   * @param name - name of instructor
   * @param place - place of instructor
   */
  @Override
  public void onDetailsRequested(String name, String place)
  {
    Intent intent = new Intent(this, DetailsActivity.class);
    intent.putExtra(ArchitectureSample.NAME, name);
    intent.putExtra(ArchitectureSample.PLACE, place);
    startActivity(intent);
  }
  
  /**
   * call Google Sheets Api with range A{loadStartItemBound}:B{loadEndItemBound}
   * @param loadStartItemBound - start bound for the range
   * @param loadEndItemBound - end bound for the range
   * @throws IllegalArgumentException - is thrown in case of illegal arguments values
   */
  @Override
  public void loadItems(int loadStartItemBound, int loadEndItemBound) throws IllegalArgumentException
  {
    if ((loadStartItemBound >= 0) &&
        (loadEndItemBound >= 0) &&
        (loadStartItemBound <= loadEndItemBound))
    {
      Toast.makeText(this, "Load A" + loadStartItemBound + ":B" + loadEndItemBound, Toast.LENGTH_LONG).show();
      
      String range = String.format(Locale.getDefault(), RANGE_TEMPLATE, loadStartItemBound, loadEndItemBound);
  
      ArchitectureSample.getInstance().getGoogleSheetsApi()
          .get(GoogleSheetsApi.SPREADSHEET_ID, range, GoogleSheetsApi.API_KEY)
          .enqueue(instructorAdapter);
    }
    else
    {
      throw new IllegalArgumentException("Illegal arguments for range");
    }
  }
  
  @Override
  public void setLoading(boolean loading)
  {
    this.loading = loading;
  }
  
  /**
   * inner class - custom OnScrollListener to add more items
   */
  class OnYogaInstructorScrollListener extends OnScrollListener
  {
    private OnLoadMoreItemsListener onLoadMoreItemsListener;
    
    /**
     * Callback method to be invoked when the RecyclerView has been scrolled. This will be
     * called after the scroll has completed.
     * This callback will also be called if visible item range changes after a layout
     * calculation. In that case, dx and dy will be 0.
     * @param recyclerView The RecyclerView which scrolled.
     * @param dx The amount of horizontal scroll.
     * @param dy The amount of vertical scroll.
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy)
    {
      super.onScrolled(recyclerView, dx, dy);
  
      if(!loading)
      {
        int visibleItemCount  = layoutManager.getChildCount();
        int totalItemCount    = layoutManager.getItemCount();
        int firstVisibleItems = layoutManager.findFirstVisibleItemPosition();
  
        if(!loading)
        {
          if((visibleItemCount + firstVisibleItems) >= totalItemCount)
          {
            loading = true;
            if(onLoadMoreItemsListener != null)
            {
              // calculate new bound values to call Api
              loadStartItemBound = totalItemCount;
              loadEndItemBound = totalItemCount + LOAD_ELEMENTS;
  
              onLoadMoreItemsListener.loadItems(loadStartItemBound, loadEndItemBound);
            }
          }
        }
      }
    }
  
    /**
     * setter
     * @param onLoadMoreItemsListener - callback interface to load more items
     */
    void setOnLoadMoreItemsListener(OnLoadMoreItemsListener onLoadMoreItemsListener)
    {
      this.onLoadMoreItemsListener = onLoadMoreItemsListener;
    }
  }
}

/**
 * callback interface to load more items
 */
interface OnLoadMoreItemsListener
{
  void loadItems(int loadStartItemBound, int loadEndItemBound) throws IllegalArgumentException;
  void setLoading(boolean loading);
}