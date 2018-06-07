package com.openyogaland.denis.architecturesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;

import java.util.ArrayList;
import java.util.List;

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
  private List<YogaInstructor>  list = new ArrayList<>();
  
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  
    // find views by id
    RecyclerView nameRecyclerView = findViewById(R.id.nameRecyclerView);
  
    // set layout manager
    LayoutManager nameLayoutManager = new LinearLayoutManager(this);
    nameRecyclerView.setLayoutManager(nameLayoutManager);
    // set adapter
    YogaInstructorAdapter yogaInstructorAdapter = new YogaInstructorAdapter(list, this);
    // TODO set listener
    nameRecyclerView.setAdapter(yogaInstructorAdapter);
    
    // TODO add variable range option
    ArchitectureSample.getInstance().getGoogleSheetsApi()
        .get(GoogleSheetsApi.SPREADSHEET_ID, "A1:B25", GoogleSheetsApi.API_KEY)
        .enqueue(yogaInstructorAdapter);
  }
}
