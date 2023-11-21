package com.example.mswproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static ArrayList<MealData> localDataSet;


    //클릭 이벤트
    public interface OnItemClickListener {
        void onItemClicked(int position, int data);
    }

    // OnItemClickListener 참조 변수 선언
    private OnItemClickListener itemClickListener;

    // OnItemClickListener 전달 메소드
    public void setOnItemClickListener (OnItemClickListener listener) {
        itemClickListener = listener;
    }

    //===== 뷰홀더 클래스 =====================================================
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewRestaurant;
        private TextView textViewDate;
        private TextView textViewDish;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRestaurant = itemView.findViewById(R.id.textViewRestaurant);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewDish = itemView.findViewById(R.id.textViewDish);
        }

        public int getIndex(int position){
            return localDataSet.get(position).index;
        }
    }

    //-생성자
    // 생성자를 통해서 데이터를 전달받도록 함
    public CustomAdapter(ArrayList<MealData> dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override   // ViewHolder 객체를 생성하여 리턴
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meal_list_item, parent, false);

        CustomAdapter.ViewHolder viewHolder = new CustomAdapter.ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener(){
            //@override
            public void onClick(View view){
                int idx = -1;
                int position = viewHolder.getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    idx = viewHolder.getIndex(position);
                }
                itemClickListener.onItemClicked(position, idx);
            }
        });

        return viewHolder;
    }

    @Override   // ViewHolder안의 내용을 position에 해당되는 데이터로 교체
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        MealData ld = localDataSet.get(position);
        holder.textViewRestaurant.setText(ld.location);
        holder.textViewDate.setText(ld.startDate + " ~ " + ld.endDate);
        holder.textViewDish.setText(ld.name);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}