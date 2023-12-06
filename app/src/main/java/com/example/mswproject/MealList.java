package com.example.mswproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mswproject.CustomCalendar;
import com.example.mswproject.CustomAdapter;
import com.example.mswproject.DBHelper;
import com.example.mswproject.R;

import java.util.ArrayList;

public class MealList extends AppCompatActivity {
    private DBHelper dbHelper;
    private SQLiteDatabase sqliteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_list);

        dbHelper = new DBHelper(this);
        try {
            sqliteDB = dbHelper.getReadableDatabase();

            RecyclerView recyclerView = findViewById(R.id.recyclerView);

            ArrayList<String> id = new ArrayList<>();
            ArrayList<String> date = new ArrayList<>();
            ArrayList<String> dish = new ArrayList<>();
            ArrayList<String> place = new ArrayList<>();
            ArrayList<String> photo = new ArrayList<>();

            CustomAdapter customAdapter;

            try (Cursor cursor = sqliteDB.rawQuery("SELECT * FROM meallist ORDER BY start_time DESC;", null)) {
                while (cursor.moveToNext()) {
                    id.add(cursor.getString(0));
                    dish.add(cursor.getString(1));
                    date.add(cursor.getString(4));
                    place.add(cursor.getString(7));
                    photo.add(cursor.getString(10));
                }
            }

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);

            customAdapter = new CustomAdapter(id, dish, date, place, photo);
            recyclerView.setAdapter(customAdapter);

            // 아이템 클릭 리스너 설정...

        } finally {
            // 항상 닫히도록 하기 위해 finally 블록에서 데이터베이스를 닫습니다.
            if (sqliteDB != null) {
                sqliteDB.close();
            }
        }

//        Button calendarBtn = findViewById(R.id.buttonCalendar);
//        calendarBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), CustomCalendar.class);
//                startActivity(intent);
//            }
//        });
    }

    // 액티비티가 소멸될 때 데이터베이스가 닫히도록 하기 위해 onDestroy를 재정의합니다.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sqliteDB != null) {
            sqliteDB.close();
        }
    }
}
