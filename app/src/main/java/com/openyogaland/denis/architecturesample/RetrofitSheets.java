package com.openyogaland.denis.architecturesample;

import retrofit2.Retrofit;

public class RetrofitSheets implements GoogleSheets
{
  Retrofit retrofit = new Retrofit.Builder()
      .baseUrl("https://sheets.googleapis.com")
      .build();
  
  GoogleSheets sheets = retrofit.create(GoogleSheets.class);
  // Call<List<Repo>> repos = service.listRepos(“Gino Osahon”);
}
