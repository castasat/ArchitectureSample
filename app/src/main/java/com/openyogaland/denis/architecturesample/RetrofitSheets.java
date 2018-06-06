package com.openyogaland.denis.architecturesample;

import com.google.api.services.sheets.v4.model.ValueRange;

import retrofit2.Call;
import retrofit2.Retrofit;

public class RetrofitSheets implements GoogleSheets
{
  private Retrofit retrofit = new Retrofit.Builder()
      .baseUrl("https://sheets.googleapis.com")
      .build();
  GoogleSheets spreadsheet = retrofit.create(GoogleSheets.class);
  
  @Override
  public Call<ValueRange> get(String spreadsheetId, String range, String apiKey)
  {
    return null;
  }
}
