package com.example.mswproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class CustomCalendar extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView diaryTextView, textViewTitle, textViewBeverage,
            textViewBreakfast, textViewLunch, textViewDinner, textViewDayCal, textViewDayCalContent,
            textViewBreakfastContent, textViewLunchContent, textViewDinnerContent, textViewBeverageContent;
    private Button cha_Btn;

    private DBHelper dbHelper;
    private SQLiteDatabase sqliteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        initializeViews();

        dbHelper = new DBHelper(this);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            handleDateSelection(year, month, dayOfMonth);
        });
    }

    private void initializeViews() {
        calendarView = findViewById(R.id.calendarView);
        diaryTextView = findViewById(R.id.diaryTextView);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewBreakfast = findViewById(R.id.textViewBreakfast);
        textViewLunch = findViewById(R.id.textViewLunch);
        textViewDinner = findViewById(R.id.textViewDinner);
        textViewBeverage = findViewById(R.id.textViewBeverage);
        textViewBreakfastContent = findViewById(R.id.textViewBreakfastContent);
        textViewLunchContent = findViewById(R.id.textViewLunchContent);
        textViewDinnerContent = findViewById(R.id.textViewDinnerContent);
        textViewBeverageContent = findViewById(R.id.textViewBeverageContent);
        textViewDayCalContent = findViewById(R.id.textViewDayCalContent);
        textViewDayCal = findViewById(R.id.textViewDayCal);
    }

    private void handleDateSelection(int year, int month, int dayOfMonth) {
        textViewBreakfast.setVisibility(View.VISIBLE);
        textViewLunch.setVisibility(View.VISIBLE);
        textViewDinner.setVisibility(View.VISIBLE);
        textViewBeverage.setVisibility(View.VISIBLE);
        textViewBreakfastContent.setVisibility(View.VISIBLE);
        textViewLunchContent.setVisibility(View.VISIBLE);
        textViewDinnerContent.setVisibility(View.VISIBLE);
        textViewBeverageContent.setVisibility(View.VISIBLE);
        textViewDayCalContent.setVisibility(View.VISIBLE);
        textViewDayCal.setVisibility(View.VISIBLE);

        diaryTextView.setText(String.format("%d / %d / %d", year, month + 1, dayOfMonth));
        diaryTextView.setTextSize(24);

        textViewBreakfastContent.setText("아침을 먹지 않았어요!");
        textViewLunchContent.setText("점심을 먹지 않았어요!");
        textViewDinnerContent.setText("저녁을 먹지 않았어요!");
        textViewBeverageContent.setText("음료를 마시지 않았어요!");

        String date = String.format("%04d%02d%02d", year, month + 1, dayOfMonth);

        try {
            sqliteDB = dbHelper.getReadableDatabase();
            populateMealContent(date, "아침", textViewBreakfastContent);
            populateMealContent(date, "점심", textViewLunchContent);
            populateMealContent(date, "저녁", textViewDinnerContent);
            populateMealContent(date, "음료", textViewBeverageContent);

            try(Cursor cursor = sqliteDB.rawQuery("SELECT SUM(calories) FROM meallist WHERE start_time LIKE ?;", new String[]{date + "%"})){
                if(cursor.moveToFirst()){
                    textViewDayCalContent.setText(cursor.getString(0) + " kacl");
                }
            }
        } finally {
            if (sqliteDB != null) {
                sqliteDB.close();
            }
        }
    }

    private void populateMealContent(String date, String category, TextView textView) {
        try (Cursor cursor = sqliteDB.rawQuery("SELECT dish, place, calories FROM meallist WHERE category LIKE ? AND start_time LIKE ?;", new String[]{category, date + "%"})) {
            if (cursor.moveToFirst()) {
                String temp = cursor.getString(0) + " / " +  cursor.getString(1) + " / " + cursor.getString(2) + "kcal";
                textView.setText(temp);
            }
        }
    }
}