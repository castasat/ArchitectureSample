package com.openyogaland.denis.architecturesample;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.openyogaland.denis.architecturesample.InstructorAdapter.YogaInstructorViewHolder;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.openyogaland.denis.architecturesample.ArchitectureSample.log;

public class InstructorAdapter extends
                               Adapter<YogaInstructorViewHolder> implements
                                                                 Callback<GoogleSheetsResponse>,
                                                                 OnClickListener
{
  // fields
  private final ArrayList<YogaInstructor>  instructors;
  private OnDetailsRequestedListener       onDetailsRequestedListener;
  private OnLoadMoreItemsListener          onLoadMoreItemsListener;
  
  /**
   * constructor
   * @param instructors - instructors of YogaInstructor elements
   */
  InstructorAdapter(@Nullable final ArrayList<YogaInstructor> instructors)
  {
    super();
    this.instructors = new ArrayList<>();
    this.instructors.addAll(instructors);
  }
  
  /**
   * Number of items in the data set held by the adapter (invoked by LayoutManager).
   * @return The total number of items in this adapter.
   */
  @Override
  public final int
  getItemCount()
  {
    return instructors.size();
  }
  
  /**
   * Inner static class YogaInstructorViewHolder
   * Provides a reference to the views for each data item
   */
  static class
  YogaInstructorViewHolder extends
                           ViewHolder
  {
    final FrameLayout itemFrameLayout;
    final TextView    itemTextView;
    
    /**
     * constructor
     * @param itemView - root or parent layout element for the item
     */
    YogaInstructorViewHolder(final View itemView)
    {
      super(itemView);
      itemFrameLayout = itemView.findViewById(R.id.itemCardView);
      itemTextView    = itemView.findViewById(R.id.itemTextView);
    }
  }
  
  /**
   * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
   * an item.
   * This new ViewHolder should be constructed with a new View that can represent the items
   * of the given type. You can either create a new View manually or inflate it from an XML
   * layout file.
   * The new ViewHolder will be used to display items of the adapter using
   * onBindViewHolder(ViewHolder, int, List). Since it will be re-used to display
   * different items in the data set, it is a good idea to cache references to sub views of
   * the View to avoid unnecessary {view.findViewById(int)} calls.
   * @param parent The ViewGroup into which the new View will be added after it is bound to
   * an adapter position.
   * @param viewType The view type of the new View.
   * @return A new ViewHolder that holds a View of the given view type.
   * @see #getItemViewType(int)
   * @see #onBindViewHolder(ViewHolder, int)
   */
  @Override
  @NonNull
  public final YogaInstructorViewHolder
  onCreateViewHolder(@Nullable ViewGroup parent,
                     int viewType)
  {
    YogaInstructorViewHolder result = null;
    
    if(parent != null)
    {
      LayoutInflater inflater  =
      LayoutInflater.from(parent.getContext());
      
      // create new View
      View listItemView =
      inflater.inflate(R.layout.name_list_item, parent,false);
      
      result = new YogaInstructorViewHolder(listItemView);
    }
  
    // if there are different view types (Header, Footer, etc) use viewType
    
    return result;
  }
  
  /**
   * Called by RecyclerView to display the data at the specified position. This method should
   * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
   * position.
   * <p>
   * Note that unlike ListView, RecyclerView will not call this method
   * again if the position of the item changes in the data set unless the item itself is
   * invalidated or the new position cannot be determined. For this reason, you should only
   * use the {@code position} parameter while acquiring the related data item inside
   * this method and should not keep a copy of it. If you need the position of an item later
   * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
   * have the updated adapter position.
   *
   * Override onBindViewHolder(ViewHolder, int, List) instead if Adapter can
   * handle efficient partial bind.
   * @param viewHolder The ViewHolder which should be updated to represent the contents of the
   * item at the given position in the data set.
   * @param position The position of the item within the adapter's data set.
   */
  @Override
  public final void
  onBindViewHolder(@Nullable YogaInstructorViewHolder viewHolder,
                   int position)
  {
    // fill View with data from YogaInstructor class
    YogaInstructor instructor = getYogaInstructor(position);
    TextView itemTextView = viewHolder.itemTextView;
    itemTextView.setText(instructor.getName());
    itemTextView.setTag(Integer.valueOf(position));
    itemTextView.setOnClickListener(this);
  }
  
  /**
   * Called by RecyclerView when it starts observing this Adapter.
   * Keep in mind that same adapter may be observed by multiple RecyclerViews.
   * @param recyclerView The RecyclerView instance which started observing this adapter.
   * @see #onDetachedFromRecyclerView(RecyclerView)
   */
  @Override
  public final void
  onAttachedToRecyclerView(@NonNull RecyclerView recyclerView)
  {
    super.onAttachedToRecyclerView(recyclerView);
  }
  
  /**
   * @param position of item
   * @return item id by it's position
   * @throws IllegalArgumentException if position is greater than the size of the instructors
   */
  @Contract(pure = true)
  @Override
  public final long
  getItemId(int position)
  {
    return (long) position;
  }
  
  /**
   * @param position - position of item
   * @return Object item by it's position
   * @throws IllegalArgumentException if position is greater than the size of the instructors
   */
  private Object
  getItem(int position)
  {
    return instructors.get(position);
  }
  
  /**
   * @param position - position of item
   * @return YogaInstructor by it's position
   * @throws IllegalArgumentException if position is greater than the size of the instructors
   */
  private YogaInstructor
  getYogaInstructor(int position)
  {
    return ((YogaInstructor) getItem(position));
  }
  
  @Override
  public final void
  onResponse(@Nullable Call<GoogleSheetsResponse> call,
             @Nullable Response<GoogleSheetsResponse> response)
  {
    GoogleSheetsResponse body = response.body();
    
    if(body != null)
    {
      int size = body.getValues().size();
      instructors.ensureCapacity(size);
      
      for(int i = 0; i < size; i++)
      {
        ArrayList<String> item           = body.getValues().get(i);
        YogaInstructor    yogaInstructor = new YogaInstructor(item);
        instructors.add(i, yogaInstructor);
        notifyDataSetChanged();
        if (onLoadMoreItemsListener != null)
        {
          onLoadMoreItemsListener.setLoading(false);
        }
        
        log(instructors.get(i).getName());
      }
    }
  }
  
  @Override
  public final void
  onFailure(@Nullable Call<GoogleSheetsResponse> call,
                              @Nullable Throwable throwable)
  {
    log("Error occurred during networking " + throwable);
  }
  
  /**
   * Called when a view has been clicked.
   * @param view The view that was clicked.
   */
  @Override
  public final void
  onClick(View view)
  {
    if((view instanceof TextView) &&
       (view.getTag() != null))
    {
      YogaInstructor instructor =
      instructors.get(((Integer) view.getTag()).intValue());
      
      // log("instructor.name = " + instructor.getName());
      
      if (onDetailsRequestedListener != null)
      {
        onDetailsRequestedListener
        .onDetailsRequested(instructor.getName(),
                            instructor.getPlace());
      }
    }
  }
  
  public final void
  setOnDetailsRequestedListener(OnDetailsRequestedListener
                                onDetailsRequestedListener)
  {
    this.onDetailsRequestedListener =
    onDetailsRequestedListener;
  }
  
  public final void
  setOnLoadMoreItemsListener(OnLoadMoreItemsListener
                             onLoadMoreItemsListener)
  {
    this.onLoadMoreItemsListener =
    onLoadMoreItemsListener;
  }
}

interface OnDetailsRequestedListener
{
  void onDetailsRequested(String name, String place);
}