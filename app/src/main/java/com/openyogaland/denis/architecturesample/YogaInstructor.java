package com.openyogaland.denis.architecturesample;

import com.google.gson.annotations.SerializedName;

/**
 * Simple POJO that matches each field in the JSON response object gotten from querying an API
 * with getters and setters for each field
 */
public class YogaInstructor
{
  /**
   * fields
   */
  @SerializedName("who")
  private String who;
  
  @SerializedName("where")
  private String where;

  /**
   * constructor
   * @param who   - the name of yoga instructor or yoga club
   * @param where - place or a list of places
   */
  public YogaInstructor(String who, String where)
  {
    this.who   = who;
    this.where = where;
  }
  
  /**
   * getter
   * @return who - the name of yoga instructor or yoga club
   */
  public String getWho()
  {
    return who;
  }
  
  /**
   * setter
   * @param who - the name of yoga instructor or yoga club
   */
  public void setWho(String who)
  {
    this.who = who;
  }
  
  /**
   * getter
   * @return where - place or a list of places
   */
  public String getWhere()
  {
    return where;
  }
  
  /**
   * setter
   * @param where - place or a list of places
   */
  public void setWhere(String where)
  {
    this.where = where;
  }
}
