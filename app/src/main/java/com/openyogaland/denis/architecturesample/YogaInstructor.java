package com.openyogaland.denis.architecturesample;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Simple POJO that matches each field in the JSON response object gotten from querying an API
 * with getters and setters for each field
 */
public class
YogaInstructor
{
  
  @SerializedName("instructors")
  private ArrayList<String> instructors = null;
  
  @SerializedName("name")
  private String name;
  
  @SerializedName("place")
  private String place;
  
  /**
   * No args constructor for use in serialization
   */
  public
  YogaInstructor() {}
  
  /**
   * constructor
   * @param instructors - instructors of two String values name and place
   */
  YogaInstructor
  (@NotNull ArrayList<String>
   instructors)
  {
    this.instructors = instructors;
    
    this.name = instructors.get(0);
    this.place = instructors.get(1);
  }
  
  /**
   * getter
   * @return instructors - instructors of two String values name and place
   */
  public ArrayList<String>
  getInstructors()
  {
    return
    instructors;
  }
  
  /**
   * setter
   * @param instructors - instructors of two String values name and place
   */
  public void
  setInstructors
  (ArrayList<String> instructors)
  {
    this.instructors =
    instructors;
  }
  
  /**
   * getter
   * @return name - name of yoga instructor
   */
  public String
  getName()
  {
    return
    name;
  }
  
  /**
   * setter
   * @param name - name of yoga instructor
   */
  public void
  setName(String name)
  {
    this.name =
    name;
  }
  
  /**
   * getter
   * @return place - the places place yoga instructor works
   */
  public String
  getPlace()
  {
    return
    place;
  }
  
  /**
   * setter
   * @param place - the place where yoga instructor works
   */
  public void
  setPlace(String place)
  {
    this.place =
    place;
  }
}
