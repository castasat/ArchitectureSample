package com.openyogaland.denis.architecturesample;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple POJO that matches each field in the JSON response object gotten from querying an API
 * with getters and setters for each field
 */
public class YogaInstructor
{
  
  @SerializedName("list")
  private List<String> list = null;
  
  @SerializedName("name")
  private String name;
  
  @SerializedName("place")
  private String place;
  
  /**
   * No args constructor for use in serialization
   */
  public YogaInstructor() {}
  
  /**
   * constructor
   * @param list - list of two String values name and place
   */
  public YogaInstructor(@NotNull List<String> list)
  {
    this.list  = (ArrayList<String>) list;
    
    this.name = list.get(0);
    this.place = list.get(1);
  }
  
  /**
   * getter
   * @return list - list of two String values name and place
   */
  public List<String> getList()
  {
    return list;
  }
  
  /**
   * setter
   * @param list - list of two String values name and place
   */
  public void setList(List<String> list)
  {
    this.list = list;
  }
  
  /**
   * getter
   * @return name - nanme of yoga instructor
   */
  public String getName()
  {
    return name;
  }
  
  /**
   * setter
   * @param name - nanme of yoga instructor
   */
  public void setName(String name)
  {
    this.name = name;
  }
  
  /**
   * getter
   * @return place - the places place yoga instructor works
   */
  public String getPlace()
  {
    return place;
  }
  
  /**
   * setter
   * @param place - the place where yoga instructor works
   */
  public void setPlace(String place)
  {
    this.place = place;
  }
}
