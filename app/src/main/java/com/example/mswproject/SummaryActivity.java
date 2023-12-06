package com.example.mswproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class SummaryActivity extends AppCompatActivity {
    TextView textViewTitle, textViewSide, textViewCategory, textViewDish, textViewPlace, textViewDate, textViewCost, textViewCal, textViewReview;
    ImageView imageViewPhoto;

    String itemID;

    private DBHelper dbHelper;
    private SQLiteDatabase sqliteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        itemID = Integer.toString(getIntent().getIntExtra("ITEM_ID", -1));

        textViewTitle = findViewById(R.id.textViewTitle3);
        textViewCategory = findViewById(R.id.textViewCategory);
        textViewDish = findViewById(R.id.textViewDishName);
        textViewPlace = findViewById(R.id.textViewPlace);
        textViewDate = findViewById(R.id.textViewTime);
        textViewCost = findViewById(R.id.textViewCost);
        textViewCal = findViewById(R.id.textViewCal);
        textViewReview = findViewById(R.id.textViewReview);
        textViewSide = findViewById(R.id.textViewSide);

        imageViewPhoto = findViewById(R.id.imageViewPhoto2);

        dbHelper = new DBHelper(this);

        try {
            sqliteDB = dbHelper.getReadableDatabase();

            try (Cursor cursor = sqliteDB.rawQuery("SELECT * FROM meallist WHERE id = " + itemID + ";", null)) {
                if (cursor.moveToFirst()) {
                    textViewSide.setText(cursor.getString(2));
                    textViewCategory.setText(cursor.getString(3));
                    textViewDish.setText(cursor.getString(1));
                    textViewPlace.setText(cursor.getString(7));
                    textViewCost.setText(cursor.getString(6) + " 원");
                    textViewCal.setText(cursor.getString(8) + " kcal");
                    textViewReview.setText(cursor.getString(9));

                    String startTemp = cursor.getString(4);
                    String startTemp2 = startTemp.substring(0, 4) + "/" + startTemp.substring(4, 6) + "/" + startTemp.substring(6, 8) + " " +
                            startTemp.substring(8, 10) + ":" + startTemp.substring(10, 12);

                    String endTemp = cursor.getString(5);
                    String endTemp2 = endTemp.substring(0, 4) + "/" + endTemp.substring(4, 6) + "/" + endTemp.substring(6, 8) + " " +
                            endTemp.substring(8, 10) + ":" + endTemp.substring(10, 12);

                    textViewTitle.setText(startTemp2);
                    textViewDate.setText(startTemp2 + "\n~ " + endTemp2);

                    String imagePath = cursor.getString(10);
                    if (imagePath != null) {
                        File imgFile = new File(imagePath);
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        imageViewPhoto.setImageBitmap(myBitmap);
                    }
                }
            }
        } finally {
            if (sqliteDB != null) {
                sqliteDB.close();
            }
        }
    }

    public void delItem(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("삭제")
                .setMessage("식사 데이터를 삭제할까요?")
                .setPositiveButton("예", (dialog, which) -> {
                    // User clicked Yes, delete the data
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.delete("meallist", "id = ?", new String[]{itemID});

                    Intent intent = new Intent(getApplicationContext(), MealList.class);
                    startActivity((intent));
                    finish();
                })
                .setNegativeButton("아니오", (dialog, which) -> {
                    // User clicked No, do nothing
                    dialog.dismiss();
                })
                .show();
    }
}

