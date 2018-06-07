package com.openyogaland.denis.architecturesample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.openyogaland.denis.architecturesample.ArchitectureSample.log;

public class YogaInstructorAdapter extends Adapter implements Callback<GoogleSheetsResponse>
{
  // fields
  private List<YogaInstructor> list;
  
  public YogaInstructorAdapter(@NonNull List<YogaInstructor> list, @NonNull Context context)
  {
    // TODO
  }
  
  @Override
  public void onResponse(@NonNull Call<GoogleSheetsResponse> call,
                         @NonNull Response<GoogleSheetsResponse> response)
  {
    GoogleSheetsResponse body = response.body();
    
    if(body != null)
    {
      int size = body.getValues().size();
      list = new ArrayList<>(size);
      
      for(int i = 0; i < size; i++)
      {
        List<String>   element    = body.getValues().get(i);
        YogaInstructor instructor = new YogaInstructor(element);
        list.add(i, instructor);
        
        //log(list.get(i).getName());
      }
    }
  }
  
  @Override
  public void onFailure(@NonNull Call<GoogleSheetsResponse> call, @NonNull Throwable t)
  {
    log("Error occurred during networking " + t);
  }
  
  /**
   * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
   * an item.
   * <p>
   * This new ViewHolder should be constructed with a new View that can represent the items
   * of the given type. You can either create a new View manually or inflate it from an XML
   * layout file.
   * <p>
   * The new ViewHolder will be used to display items of the adapter using
   * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
   * different items in the data set, it is a good idea to cache references to sub views of
   * the View to avoid unnecessary {view.findViewById(int)} calls.
   * @param parent The ViewGroup into which the new View will be added after it is bound to
   * an adapter position.
   * @param viewType The view type of the new View.
   * @return A new ViewHolder that holds a View of the given view type.
   * @see #getItemViewType(int)
   * @see #onBindViewHolder(ViewHolder, int)
   */
  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
  {
    // TODO
    return null;
  }
  
  /**
   * Called by RecyclerView to display the data at the specified position. This method should
   * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
   * position.
   * <p>
   * Note that unlike {@link ListView}, RecyclerView will not call this method
   * again if the position of the item changes in the data set unless the item itself is
   * invalidated or the new position cannot be determined. For this reason, you should only
   * use the <code>position</code> parameter while acquiring the related data item inside
   * this method and should not keep a copy of it. If you need the position of an item later
   * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
   * have the updated adapter position.
   *
   * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
   * handle efficient partial bind.
   * @param holder The ViewHolder which should be updated to represent the contents of the
   * item at the given position in the data set.
   * @param position The position of the item within the adapter's data set.
   */
  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position)
  {
  }
  
  /**
   * Returns the total number of items in the data set held by the adapter.
   * @return The total number of items in this adapter.
   */
  @Override
  public int getItemCount()
  {
    return 0;
  }
}
