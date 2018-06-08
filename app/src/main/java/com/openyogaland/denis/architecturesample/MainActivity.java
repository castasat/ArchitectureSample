package com.openyogaland.denis.architecturesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;

import java.util.ArrayList;

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
  // fields
  private ArrayList<YogaInstructor> instructors = new ArrayList<>();
  
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  
    YogaInstructorAdapter yogaInstructorAdapter = new YogaInstructorAdapter(instructors);
    
    // TODO check internet connection
    // TODO add variable range option
    ArchitectureSample.getInstance().getGoogleSheetsApi()
        .get(GoogleSheetsApi.SPREADSHEET_ID, "A1:B25", GoogleSheetsApi.API_KEY)
        .enqueue(yogaInstructorAdapter);
    
    RecyclerView  yogaInstructorRecyclerView  = findViewById(R.id.yogaInstructorRecyclerView);
    LayoutManager yogaInstructorLayoutManager = new LinearLayoutManager(this);
    yogaInstructorRecyclerView.setLayoutManager(yogaInstructorLayoutManager);
    yogaInstructorRecyclerView.setAdapter(yogaInstructorAdapter);
  }
  
  public void onDetailsRequested(YogaInstructor yogaInstructor)
  {
    Intent intent = new Intent(this, DetailsActivity.class);
    intent.putExtra("name", yogaInstructor.getName());
    intent.putExtra("place", yogaInstructor.getPlace());
    startActivity(intent);
  }
}
