package com.example.mswproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Analyzation extends AppCompatActivity {
    TextView textViewTotalCal, textViewTotalBreakfast, textViewTotalLunch, textViewTotalDinner, textViewTotalBeverage;
    private DBHelper dbHelper;
    private SQLiteDatabase sqliteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyzation);

        textViewTotalCal = findViewById(R.id.textViewTotalCal);
        textViewTotalBreakfast = findViewById(R.id.textViewTotalBreakfast);
        textViewTotalLunch = findViewById(R.id.textViewTotalLunch);
        textViewTotalDinner = findViewById(R.id.textViewTotalDinner);
        textViewTotalBeverage = findViewById(R.id.textViewTotalBeverage);

        // Initialize DBHelper
        dbHelper = new DBHelper(this);

        try {
            sqliteDB = dbHelper.getReadableDatabase();

            // Get the current date and time
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDateTime dateThirtyDaysAgo = currentDateTime.minusDays(30);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

            // Format dates for comparison
            String dateUpperBound = currentDateTime.format(formatter);
            String dateLowerBound = dateThirtyDaysAgo.format(formatter);

            // Total Calories
            try (Cursor cursor = sqliteDB.rawQuery("SELECT SUM(calories) FROM meallist WHERE start_time >= ?;", new String[]{dateLowerBound})) {
                if (cursor.moveToFirst()) {
                    textViewTotalCal.setText(cursor.getString(0));
                }
            }

            // Total Breakfast Cost
            try (Cursor cursor = sqliteDB.rawQuery("SELECT SUM(cost) FROM meallist WHERE category like '아침' AND start_time >= ?;", new String[]{dateLowerBound})) {
                if (cursor.moveToFirst()) {
                    textViewTotalBreakfast.setText(cursor.getString(0));
                }
            }

            // Total Lunch Cost
            try (Cursor cursor = sqliteDB.rawQuery("SELECT SUM(cost) FROM meallist WHERE category like '점심' AND start_time >= ?;", new String[]{dateLowerBound})) {
                if (cursor.moveToFirst()) {
                    textViewTotalLunch.setText(cursor.getString(0));
                }
            }

            // Total Dinner Cost
            try (Cursor cursor = sqliteDB.rawQuery("SELECT SUM(cost) FROM meallist WHERE category like '저녁' AND start_time >= ?;", new String[]{dateLowerBound})) {
                if (cursor.moveToFirst()) {
                    textViewTotalDinner.setText(cursor.getString(0));
                }
            }

            // Total Beverage Cost
            try (Cursor cursor = sqliteDB.rawQuery("SELECT SUM(cost) FROM meallist WHERE category like '음료' AND start_time >= ?;", new String[]{dateLowerBound})) {
                if (cursor.moveToFirst()) {
                    textViewTotalBeverage.setText(cursor.getString(0));
                }
            }
        } finally {
            if (sqliteDB != null) {
                sqliteDB.close();
            }
        }
    }
}