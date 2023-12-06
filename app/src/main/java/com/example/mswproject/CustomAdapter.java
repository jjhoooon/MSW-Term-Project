package com.example.mswproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static ArrayList<String> id, dish, date, place, photo;

    ViewHolder viewHolder;


    //===== 뷰홀더 클래스 =====================================================
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewRestaurant;
        private TextView textViewDate;
        private TextView textViewDish;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRestaurant = itemView.findViewById(R.id.textViewRestaurant);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewDish = itemView.findViewById(R.id.textViewDish);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    int position = getAdapterPosition();
                    int selectedID = Integer.parseInt(id.get(position));

                    Intent intent = new Intent(view.getContext(), SummaryActivity.class);
                    intent.putExtra("ITEM_ID", selectedID);
                    view.getContext().startActivity(intent);
                }
            });
        }

        public int getIndex(int position) {
            return Integer.valueOf(id.get(position));
        }
    }

    //-생성자
    // 생성자를 통해서 데이터를 전달받도록 함
    public CustomAdapter(ArrayList<String> id, ArrayList<String> dish, ArrayList<String> date, ArrayList<String> place, ArrayList<String> photo) {
        this.id = id;
        this.dish = dish;
        this.date = date;
        this.place = place;
        this.photo = photo;
    }

    @NonNull
    @Override   // ViewHolder 객체를 생성하여 리턴
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meal_list_item, parent, false);

        CustomAdapter.ViewHolder viewHolder = new CustomAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override   // ViewHolder안의 내용을 position에 해당되는 데이터로 교체
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        holder.textViewRestaurant.setText(this.place.get(position));
        holder.textViewDish.setText(this.dish.get(position));

        String dateTemp = this.date.get(position);
        String dateTemp2 = dateTemp.substring(0, 4) + "/" + dateTemp.substring(4, 6) + "/" + dateTemp.substring(6, 8) + " " +
                dateTemp.substring(8, 10) + ":" + dateTemp.substring(10, 12);
        holder.textViewDate.setText(dateTemp2);

        String imagePath = this.photo.get(position);
        if (imagePath != null) {
            File imgFile = new File(imagePath);
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            holder.imageView.setImageBitmap(myBitmap);
        }
    }

    @Override
    public int getItemCount() {
        return this.id.size();
    }

}