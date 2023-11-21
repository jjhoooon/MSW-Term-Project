package com.example.mswproject;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MealList extends AppCompatActivity {
    SQLiteDatabase sqliteDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_list);

        ArrayList<MealData> testDataSet = new ArrayList<>();

        testDataSet.add(new MealData(1, "샐러리 스틱", "side", "조식", "2023-11-14 08:00",
                "2023-11-14 09:00", 1500, "상록원", "샐러리의 상큼함", 30.5, "image1"));
        testDataSet.add(new MealData(2, "치킨 샐러드", "main", "점심", "2023-11-14 12:00",
                "2023-11-14 13:00", 7000, "기숙사식당", "단백질 가득", 450.2, "image2"));
        testDataSet.add(new MealData(3, "토마토 스프", "main", "저녁", "2023-11-14 18:00",
                "2023-11-14 19:00", 3000, "상록원", "건강한 선택", 120.7, "image3"));
        testDataSet.add(new MealData(4, "과일 요거트", "side", "조식", "2023-11-15 08:30",
                "2023-11-15 09:30", 2500, "상록원", "달콤하고 건강한 아침", 180.3, "image4"));
        testDataSet.add(new MealData(5, "돈까스", "main", "점심", "2023-11-15 12:30",
                "2023-11-15 13:30", 8000, "그루터기식당", "고기의 고소함", 600.5, "image5"));
        testDataSet.add(new MealData(6, "새우 시금치 샐러드", "main", "저녁", "2023-11-15 18:30",
                "2023-11-15 19:30", 6000, "상록원", "다양한 식재료의 만남", 350.8, "image6"));
        testDataSet.add(new MealData(7, "요거트 파르페", "side", "조식", "2023-11-16 09:00",
                "2023-11-16 10:00", 3500, "상록원", "달콤하고 건강한 아침", 220.1, "image7"));
        testDataSet.add(new MealData(8, "비프 스파게티", "main", "점심", "2023-11-16 12:45",
                "2023-11-16 13:45", 7500, "기숙사식당", "이탈리안의 맛", 500.9, "image8"));
        testDataSet.add(new MealData(9, "콥 샐러드", "main", "저녁", "2023-11-16 19:00",
                "2023-11-16 20:00", 5500, "그루터기식당", "건강한 선택", 420.6, "image9"));
        testDataSet.add(new MealData(10, "블루베리 스무디", "side", "조식", "2023-11-17 09:30",
                "2023-11-17 10:30", 4000, "상록원", "과일의 상큼함", 150.2, "image10"));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
        recyclerView.setLayoutManager(linearLayoutManager);

        CustomAdapter customAdapter = new CustomAdapter(testDataSet);

        // 리사이클러뷰 클릭했을 때 동작
        customAdapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position, int data) {
                Toast.makeText(getApplicationContext(), "Position:" + position + ", index:" + data, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(customAdapter);

        Button CalendarBtn = (Button) findViewById(R.id.buttonCalendar);

        CalendarBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Calendar.class);
                startActivity(intent);
            }
        });
    }
}