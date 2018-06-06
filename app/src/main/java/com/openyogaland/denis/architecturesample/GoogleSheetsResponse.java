package com.openyogaland.denis.architecturesample;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoogleSheetsResponse
{
  @SerializedName("range")
  private String range;
  
  @SerializedName("majorDimension")
  private String majorDimension;
  
  @SerializedName("values")
  private List<YogaInstructor> values = null;
  
  /**
   * No args constructor for use in serialization
   */
  public GoogleSheetsResponse() {}
  
  /**
   * constructor
   * @param range - the range the values cover, in A1 notation. For output, this range indicates
   * the entire requested range, even though the values will exclude trailing rows and columns.
   * When appending values, this field represents the range to search for a table, after which
   * values will be appended.
   * @param majorDimension - the major dimension that results should use
   * @param values - the data that was read or to be written. This is an array of arrays, the
   * outer array representing all the data and each inner array representing a major dimension.
   * Each item in the inner array corresponds with one cell
   */
  public GoogleSheetsResponse(String range, String majorDimension, List<YogaInstructor> values)
  {
    this.range          = range;
    this.majorDimension = majorDimension;
    this.values         = values;
  }
  
  /**
   * getter
   * @return range - the range the values cover, in A1 notation. For output, this range indicates
   * the entire requested range, even though the values will exclude trailing rows and columns.
   * When appending values, this field represents the range to search for a table, after which
   * values will be appended.
   */
  public String getRange()
  {
    return range;
  }
  
  /**
   * setter
   * @param range - the range the values cover, in A1 notation. For output, this range
   * indicates
   * the entire requested range, even though the values will exclude trailing rows and columns.
   * When appending values, this field represents the range to search for a table, after which
   * values will be appended.
   */
  public void setRange(String range)
  {
    this.range = range;
  }
  
  /**
   * getter
   * @return majorDimension - the major dimension that results should use
   */
  public String getMajorDimension()
  {
    return majorDimension;
  }
  
  /**
   * setter
   * @param majorDimension - the major dimension that results should use
   */
  public void setMajorDimension(String majorDimension)
  {
    this.majorDimension = majorDimension;
  }
  
  /**
   * getter
   * @return values - the data that was read or to be written. This is an array of arrays, the
   * outer array representing all the data and each inner array representing a major dimension.
   * Each item in the inner array corresponds with one cell
   */
  public List<YogaInstructor> getValues()
  {
    return values;
  }
  
  /**
   * setter
   * @param values - the data that was read or to be written. This is an array of
   * arrays, the outer array representing all the data and each inner array representing a major
   * dimension. Each item in the inner array corresponds with one cell
   */
  public void setValues(List<YogaInstructor> values)
  {
    this.values = values;
  }
}
