package com.example.mswproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton showCalButton = (ImageButton) findViewById(R.id.show_cal_btn);

        showCalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomCalendar.class);
                startActivity(intent);
            }
        });

        ImageButton listBtn = (ImageButton) findViewById(R.id.listBtn);

        listBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MealList.class);
                startActivity(intent);
            }
        });

        ImageButton AnalyzeBtn = (ImageButton) findViewById(R.id.analyzeBtn);

        AnalyzeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Analyzation.class);
                startActivity(intent);
            }
        });

        ImageButton inputBtn = (ImageButton) findViewById(R.id.inputBtn);

        inputBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MealInputActivity.class);
                startActivity(intent);
            }
        });
    }
}