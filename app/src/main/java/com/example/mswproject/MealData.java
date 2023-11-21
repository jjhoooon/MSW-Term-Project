package com.example.mswproject;

public class MealData {
    int index;
    String name; //음식명
    String dishOption; // 메인 or 반찬
    String category; // 조식, 중식, 속식, 음료
    String startDate; //식사 시작 시간
    String endDate; //식사 종료 시간
    int cost; //가격
    String location; // 장소
    String review; // 리뷰
    double calories;
    String picture;

    MealData(int idx, String n, String op, String cat, String sd, String ed,
             int c, String loc, String rv, double cal, String pic){
        index = idx;
        name = n;
        dishOption = op;
        category = cat;
        startDate = sd;
        endDate = ed;
        cost = c;
        location = loc;
        review = rv;
        calories = cal;
        picture = pic;
    }
}
