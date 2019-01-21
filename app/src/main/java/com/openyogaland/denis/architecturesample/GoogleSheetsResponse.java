 package com.openyogaland.denis.architecturesample;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class
GoogleSheetsResponse
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
  private ArrayList<ArrayList<String>> values = new ArrayList<>();
  
  public
  GoogleSheetsResponse() {}
  
  /**
   * constructor
   * @param values - a list of lists with String data
   * @param range - the range given in request and response
   * @param majorDimension - ROWS (default) or COLUMNS
   */
  public
  GoogleSheetsResponse(String range,
                       String majorDimension,
                       ArrayList<ArrayList<String>> values)
  {
    this.range = range;
    this.majorDimension = majorDimension;
    this.values = values;
  }
  
  public String
  getRange()
  {
    return range;
  }
  
  public void
  setRange(String range)
  {
    this.range = range;
  }

  public String
  getMajorDimension()
  {
    return majorDimension;
  }
  
  // majorDimension - ROWS (default) or COLUMNS
  public void
  setMajorDimension(String majorDimension)
  {
    this.majorDimension = majorDimension;
  }
  
  /**
   * @return values - a list of lists with String data
   */
  @NonNull
  public ArrayList<ArrayList<String>>
  getValues()
  {
    if(values == null)
    {
      return new ArrayList<>();
    }
    return values;
  }
  
  /**
   * @param values - a list of lists with String data
   */
  public void
  setValues(ArrayList<ArrayList<String>> values)
  {
    this.values = values;
  }
}