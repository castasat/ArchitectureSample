 package com.openyogaland.denis.architecturesample;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

 public class GoogleSheetsResponse
{
  // fields
  @Expose
  @SerializedName("range")
  private String range;
  
  @Expose
  @SerializedName("majorDimension")
  private String majorDimension;
  
  @Expose
  @SerializedName("values")
  private List<List<String>> values = new ArrayList<List<String>>();
  
  /**
   * No args constructor for use in serialization
   */
  public GoogleSheetsResponse() {
  }
  
  /**
   * constructor
   * @param values - a list of lists with String data
   * @param range - the range given in request and response
   * @param majorDimension - ROWS (default) or COLUMNS
   */
  public GoogleSheetsResponse(String range, String majorDimension, List<List<String>> values)
  {
    this.range = range;
    this.majorDimension = majorDimension;
    this.values = values;
  }
  
  /**
   * getter
   * @return range - the range given in request and response
   */
  public String getRange()
  {
    return range;
  }
  
  /**
   * setter
   * @param range - the range given in request and response
   */
  public void setRange(String range)
  {
    this.range = range;
  }
  
  /**
   * getter
   * @return majorDimension - ROWS (default) or COLUMNS
   */
  public String getMajorDimension()
  {
    return majorDimension;
  }
  
  /**
   * setter
   * @param majorDimension - ROWS (default) or COLUMNS
   */
  public void setMajorDimension(String majorDimension)
  {
    this.majorDimension = majorDimension;
  }
  
  /**
   * getter
   * @return values - a list of lists with String data
   */
  @NonNull
  public List<List<String>> getValues()
  {
    if(values == null)
    {
      return new ArrayList<List<String>>();
    }
    return values;
  }
  
  /**
   * setter
   * @param values - a list of lists with String data
   */
  public void setValues(List<List<String>> values)
  {
    this.values = values;
  }
}