package com.openyogaland.denis.architecturesample;

import com.google.api.services.sheets.v4.model.ValueRange;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GoogleSheets
{
  // Every method represents one possible API call
  // HTTP annotation (GET, POST, etc.) is used to specify the request type and the relative URL
  // The return value wraps the response in a Call object with the type of the expected result
  @GET("/v4/spreadsheets/{spreadsheetId}/values/{range}")
  Call<ValueRange> get(@Path("spreadsheetId") String spreadsheetId,
                       @Query("range")        String range,
                       @Query("key")          String apiKey);
}
