package com.example.myapplication.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<FAQ> newsArrayList;

    public MyAdapter(Context context, ArrayList<FAQ> newsArrayList){
        this.context = context;
        this.newsArrayList = newsArrayList;

    }



    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        FAQ news = newsArrayList.get(position);
        holder.tvHeading.setText(news.heading);
        holder.titleImage.setImageResource(news.titleImage);
        holder.briefNews.setText(news.briefNews);

        boolean isVisible = news.visibility;
        holder.constraintLayout.setVisibility(isVisible ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvHeading;
        TextView briefNews;
        ShapeableImageView titleImage;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            tvHeading = itemView.findViewById(R.id.tvHeading);
            titleImage = itemView.findViewById(R.id.title_image);
            briefNews = itemView.findViewById(R.id.briefNews);
            constraintLayout = itemView.findViewById(R.id.expandedLayout);

            tvHeading.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    FAQ news = newsArrayList.get(getAdapterPosition());
                    news.setVisibility(!news.isVisibility());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
