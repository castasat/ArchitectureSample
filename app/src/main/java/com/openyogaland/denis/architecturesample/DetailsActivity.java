package com.openyogaland.denis.architecturesample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity
{
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.details_activity);
  
    Intent intent = getIntent();
  
    TextView nameTextView    = findViewById(R.id.nameTextView);
    TextView detailsTextView = findViewById(R.id.detailsTextView);
    
    nameTextView.setText(intent.getStringExtra("name"));
    detailsTextView.setText(intent.getStringExtra("details"));
  }
}
