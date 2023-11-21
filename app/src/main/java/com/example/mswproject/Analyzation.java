package com.example.mswproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Analyzation extends AppCompatActivity {
    TextView textViewTotalCal, textViewTotalBreakfast, textViewTotalLunch, textViewTotalDinner, textViewTotalBeverage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyzation);

        textViewTotalCal = findViewById(R.id.textViewTotalCal);
        textViewTotalBreakfast = findViewById(R.id.textViewTotalBreakfast);
        textViewTotalLunch = findViewById(R.id.textViewTotalLunch);
        textViewTotalDinner = findViewById(R.id.textViewTotalDinner);
        textViewTotalBeverage = findViewById(R.id.textViewTotalBeverage);

        // sql 써서 결과 보여주기

    }
}
