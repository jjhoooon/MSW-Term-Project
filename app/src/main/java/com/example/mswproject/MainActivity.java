package com.example.mswproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showCalButton = (Button) findViewById(R.id.show_cal_btn);

        showCalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomCalendar.class);
                startActivity(intent);
            }
        });

        Button listBtn = (Button) findViewById(R.id.listBtn);

        listBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MealList.class);
                startActivity(intent);
            }
        });

        Button AnalyzeBtn = (Button) findViewById(R.id.analyzeBtn);

        AnalyzeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Analyzation.class);
                startActivity(intent);
            }
        });

        Button inputBtn = (Button) findViewById(R.id.inputBtn);

        inputBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MealInputActivity.class);
                startActivity(intent);
            }
        });
    }
}