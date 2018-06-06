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
  
  @SerializedName("who")
  private String who;
  
  @SerializedName("where")
  private String where;
  
  /**
   * No args constructor for use in serialization
   */
  public YogaInstructor() {}
  
  /**
   * constructor
   * @param list - list of two String values who and where
   */
  public YogaInstructor(@NotNull List<String> list)
  {
    this.list  = (ArrayList<String>) list;
    
    this.who   = list.get(0);
    this.where = list.get(1);
  }
  
  /**
   * getter
   * @return list - list of two String values who and where
   */
  public List<String> getList()
  {
    return list;
  }
  
  /**
   * setter
   * @param list - list of two String values who and where
   */
  public void setList(List<String> list)
  {
    this.list = list;
  }
  
  /**
   * getter
   * @return who - nanme of yoga instructor
   */
  public String getWho()
  {
    return who;
  }
  
  /**
   * setter
   * @param who - nanme of yoga instructor
   */
  public void setWho(String who)
  {
    this.who = who;
  }
  
  /**
   * getter
   * @return where - the places where yoga instructor works
   */
  public String getWhere()
  {
    return where;
  }
  
  /**
   * setter
   * @param where - the places where yoga instructor works
   */
  public void setWhere(String where)
  {
    this.where = where;
  }
}
