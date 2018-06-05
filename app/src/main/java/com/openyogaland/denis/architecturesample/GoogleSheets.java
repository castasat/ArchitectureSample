package com.openyogaland.denis.architecturesample;

public interface GoogleSheets
{
  // Every method represents one possible API call
  // HTTP annotation (GET, POST, etc.) to specify the request type and the relative URL
  // The return value wraps the response in a Call object with the type of the expected result
  
  //@GET("users/{user}/repos")
  //Call<List<Repo>> listRepos(@Path("user") String user);
  
  //@GET("group/{id}/users")
  //Call<List<User>> groupList(@Path("id") int groupId);
  
  // Query parameters can be added to a method.
  
  //@GET(“group/{id}/users”)
  //Call<List<User>> groupList(@Path(“id”) int groupId, @Query(“sort”) String sort);
}
