package com.openyogaland.denis.architecturesample;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Singleton pattern class
 * to call Api from one single instance
 */
class ArchitectureSample
{
  // constants
  private static final String LOG_TAG = "ArchitectureSample";
  
  // fields
  private static volatile ArchitectureSample instance;
  private static          GoogleSheetsApi    googleSheetsApi;
  
  /**
   * constructor
   */
  private ArchitectureSample()
  {
    Gson gson = new GsonBuilder().create();
    Retrofit retrofit = new Builder()
        .baseUrl(GoogleSheetsApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();
    googleSheetsApi = retrofit.create(GoogleSheetsApi.class);
  }
  
  /**
   * obtain instance of singleton, double-check locking safe for threads
   * @return - the instance of singleton class
   */
  static ArchitectureSample getInstance()
  {
    ArchitectureSample localInstance = instance;
    if(localInstance == null)
    {
      synchronized(ArchitectureSample.class)
      {
        localInstance = instance;
        if(localInstance == null)
        {
          instance = new ArchitectureSample();
        }
      }
    }
    return instance;
  }
  
  /**
   * getter
   * @return googleSheetsApi - object to execute api requests
   */
  public GoogleSheetsApi getGoogleSheetsApi()
  {
    return googleSheetsApi;
  }
  
  /**
   * shorter logging
   * @param message - message to print to log
   */
  static void log(String message)
  {
    Log.d(LOG_TAG, message);
  }
}

interface GoogleSheetsApi
{
  // constants
  String BASE_URL       = "https://sheets.googleapis.com";
  String API_KEY        = "AIzaSyCVxwFcVAIy3COhJM9uiRr9iCS1KfO7nhM";
  String SPREADSHEET_ID = "1rvgAmCOWsuTVdgrcv_VXLf4xo7HU-dIcPFAdJeXsxBI";
  
  // endpoint methods declaration
  @GET("/v4/spreadsheets/{spreadsheetId}/values/{range}")
  Call<GoogleSheetsResponse> get(@Path("spreadsheetId") String spreadsheetId,
      @Path("range") String range, @Query("key") String apiKey);
}