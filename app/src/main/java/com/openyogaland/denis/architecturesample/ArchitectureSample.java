package com.openyogaland.denis.architecturesample;

import android.content.Context;
import android.support.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton pattern class
 * to call Api from one single instance
 */
class ArchitectureSample
{
  // fields
  private static volatile ArchitectureSample instance;
  private static          GoogleSheetsApi    googleSheetsApi;
  
  /**
   * constructor
   * @param context - the context of caller object
   */
  private ArchitectureSample(@NonNull @NotNull Context context)
  {
    Gson gson = new GsonBuilder()
        .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
        .create();
    
    Retrofit retrofit = new Builder()
        .baseUrl(GoogleSheetsApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();
    
    googleSheetsApi = retrofit.create(GoogleSheetsApi.class);
  }
  
  /**
   * obtain instance of singleton, double-check locking safe for threads
   * @param context - context of caller object
   * @return - the instance of singleton class
   */
  static ArchitectureSample getInstance(@NotNull Context context)
  {
    ArchitectureSample localInstance = instance;
    if(localInstance == null)
    {
      synchronized(ArchitectureSample.class)
      {
        localInstance = instance;
        if(localInstance == null)
        {
          instance = new ArchitectureSample(context);
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
}
