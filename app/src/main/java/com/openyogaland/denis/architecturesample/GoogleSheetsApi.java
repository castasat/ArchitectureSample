package com.openyogaland.denis.architecturesample;

import com.google.api.services.sheets.v4.model.ValueRange;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GoogleSheetsApi
{
  // constants
  static final String BASE_URL       = "https://sheets.googleapis.com";
  static final String API_KEY        = "AIzaSyCVxwFcVAIy3COhJM9uiRr9iCS1KfO7nhM";
  static final String SPREADSHEET_ID = "1rvgAmCOWsuTVdgrcv_VXLf4xo7HU-dIcPFAdJeXsxBI";
  
  // endpoint methods declaration
  @GET("/v4/spreadsheets/{spreadsheetId}/values/{range}")
  Call<ValueRange> get(@Path ("spreadsheetId") String spreadsheetId,
                       @Path ("range")         String range,
                       @Query("key")           String apiKey);
}
