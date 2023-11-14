package com.example.mswproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

public class Calendar extends AppCompatActivity {

    public CalendarView calendarView;
    public TextView diaryTextView, textViewTitle, textViewBeverage,
            textViewBreakfast, textViewLunch, textViewDinner;
    public Button cha_Btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarView);
        diaryTextView = findViewById(R.id.diaryTextView);
        cha_Btn = findViewById(R.id.cha_Btn);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewBreakfast = findViewById(R.id.textViewBreakfast);
        textViewLunch = findViewById(R.id.textViewLunch);
        textViewDinner = findViewById(R.id.textViewDinner);
        textViewBeverage = findViewById(R.id.textViewBeverage);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                cha_Btn.setVisibility(View.VISIBLE);
                textViewBreakfast.setVisibility(View.VISIBLE);
                textViewLunch.setVisibility((View.VISIBLE));
                textViewDinner.setVisibility(View.VISIBLE);
                textViewBeverage.setVisibility(View.VISIBLE);

                diaryTextView.setText(String.format("%d / %d / %d",year,month+1,dayOfMonth));
                diaryTextView.setTextSize(24);
            }
        });
    }

}