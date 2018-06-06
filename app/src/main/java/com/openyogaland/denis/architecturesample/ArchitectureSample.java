package com.openyogaland.denis.architecturesample;

import android.content.Context;
import android.support.annotation.NonNull;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import retrofit2.Retrofit;
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
  
  // constructor
  private ArchitectureSample(@NonNull @NotNull Context context)
  {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(GoogleSheetsApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    
    googleSheetsApi = retrofit.create(GoogleSheetsApi.class);
  }
  
  // obtain instance of singleton, double-check locking safe for threads
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
  @Contract(pure = true)
  public static GoogleSheetsApi getGoogleSheetsApi()
  {
    return googleSheetsApi;
  }
}
