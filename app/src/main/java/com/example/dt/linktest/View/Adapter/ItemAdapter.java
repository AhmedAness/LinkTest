package com.example.dt.linktest.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dt.linktest.Model.articles;
import com.example.dt.linktest.R;
import com.example.dt.linktest.View.Activities.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class ItemAdapter extends ListAdapter<articles, ItemAdapter.CourseHolder> {

    Context context;

    public ItemAdapter(Context context) {
        super(diffCallback);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<articles> diffCallback = new DiffUtil.ItemCallback<articles>() {
        @Override
        public boolean areItemsTheSame(@NonNull articles oldItem, @NonNull articles newItem) {
            return oldItem.getTitle() == newItem.getTitle();
        }

        @Override
        public boolean areContentsTheSame(@NonNull articles oldItem, @NonNull articles newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getDescription().equals(newItem.getDescription()) && oldItem.getUrl().equals(newItem.getUrl());
        }
    };

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleritem, parent, false);
        return new CourseHolder(itemview);
    }

    public articles GetCourseAt(int position) {
        return getItem(position);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder holder, int position) {
        articles item = getItem(position);
        if (!item.getUrlToImage().isEmpty()) {
            Picasso.with(context).
                    load(item.getUrlToImage()).into(holder.imageView);
            holder.Title.setText(item.getTitle());
            holder.Auth.setText("By " + item.getAuthor());
            holder.Directedat.setText(String.valueOf(item.getPublishedAt()));
        }

    }

    protected class CourseHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView Title;
        TextView Auth;
        TextView Directedat;

        public CourseHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            Title = itemView.findViewById(R.id.Title);
            Auth = itemView.findViewById(R.id.author);
            Directedat = itemView.findViewById(R.id.directedat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("selecteditem", getItem(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}