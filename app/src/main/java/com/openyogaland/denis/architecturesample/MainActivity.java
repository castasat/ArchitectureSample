package com.openyogaland.denis.architecturesample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Нужно написать андроид приложение с двумя страницами:
 *
 * 1) Прокручиваемый список элементов загружаемых с любого открытого API (например, gitHub).
 * Предусмотреть вариант, что список может быть очень длинным (скажем до 1000 позиций) и его нужно
 * будет загружать частями
 *
 * 2) При нажатии на элемент списка открывается деталка в которой показаны более подробные данные
 * по элементу списка
 *
 * 3) Архитектура приложения должна позволять его удобное мастштабирование: добавление оффлайн
 * версии, фильтров списка, создание раздела "Избранное", итд
 *
 *  Можно использовать любые библиотеки, кроме тех которые уже содержат решение задачи.
 */

public class MainActivity extends AppCompatActivity
{
  // constants
  private static final String LOG_TAG = "ArchitectureSample";
  
  // fields
  private List<YogaInstructor> list;
  
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  
    ArchitectureSample.getInstance(this).getGoogleSheetsApi()
        .get(GoogleSheetsApi.SPREADSHEET_ID, "A1:B25", GoogleSheetsApi.API_KEY)
        .enqueue(new Callback<GoogleSheetsResponse>()
        {
          @Override
          public void onResponse(
              @NonNull Call<GoogleSheetsResponse>     call,
              @NonNull Response<GoogleSheetsResponse> response)
          {
            GoogleSheetsResponse body = response.body();
            if (body != null)
            {
              log(body.getValues().toString());
            }
          }
        
          @Override
          public void onFailure(
              @NonNull Call<GoogleSheetsResponse> call,
              @NonNull Throwable t)
          {
            log("Error occurred during networking " + t);
          }
        });
  }
  
  /**
   * shorter logging
   * @param message - message to print to log
   */
  private void log(String message)
  {
    Log.d(LOG_TAG, message);
  }
}
