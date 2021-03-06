package com.openyogaland.denis.architecturesample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.openyogaland.denis.architecturesample.ArchitectureSample.NAME;
import static com.openyogaland.denis.architecturesample.ArchitectureSample.PLACE;

public class
DetailsActivity
extends AppCompatActivity
{
  @Override
  protected void
  onCreate(@Nullable
           Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.details_activity);
  
    Intent intent = getIntent();
  
    TextView nameTextView  = findViewById(R.id.nameTextView);
    TextView placeTextView = findViewById(R.id.placeTextView);
    
    nameTextView
    .setText
     (intent
      .getStringExtra
       (NAME));
    
    placeTextView
    .setText
     (intent
      .getStringExtra
       (PLACE));
  }
}

