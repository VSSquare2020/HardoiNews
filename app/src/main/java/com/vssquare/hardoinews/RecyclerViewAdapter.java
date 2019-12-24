package com.vssquare.hardoinews;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private ArrayList<Model> dataset;
    private Context context;
    RecyclerViewAdapter(ArrayList<Model> mlist, Context context){
        this.dataset=mlist;
        this.context=context;
    }
    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder{
        TextView title,subtitle;
        ImageView imageView;
        CardView posts_card;
        public ImageTypeViewHolder(View itemView){
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.subtitle = itemView.findViewById(R.id.subtitle);
            this.imageView = itemView.findViewById(R.id.Icon);
            this.posts_card = itemView.findViewById(R.id.posts_card);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postdetails,parent,false);
        return new ImageTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final Model object = dataset.get(position);
        ((ImageTypeViewHolder)holder).title.setText(object.title);
        ((ImageTypeViewHolder)holder).subtitle.setText(object.subtitle);

        ((ImageTypeViewHolder)holder).title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,WPPostDetails.class);
                intent.putExtra("itemPosition",position);
                context.startActivity(intent);
            }
        });
        ((ImageTypeViewHolder)holder).subtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,WPPostDetails.class);
                intent.putExtra("itemPosition",position);
                context.startActivity(intent);
            }
        });

        ((ImageTypeViewHolder)holder).imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,WPPostDetails.class);
                intent.putExtra("itemPosition",position);
                context.startActivity(intent);
            }
        });

        ((ImageTypeViewHolder)holder).posts_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,WPPostDetails.class);
                intent.putExtra("itemPosition",position);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
