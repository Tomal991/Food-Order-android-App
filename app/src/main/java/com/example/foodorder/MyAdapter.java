package com.example.foodorder;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList id, food, price, quan;

    MyAdapter(Activity activity, Context context, ArrayList id, ArrayList fname, ArrayList price,
                  ArrayList quan) {
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.food = fname;
        this.price = price;
        this.quan = quan;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.viewrow, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.id_txt.setText(String.valueOf(id.get(position)));
        holder.fname_txt.setText(String.valueOf(food.get(position)));
        holder.price_txt.setText(String.valueOf(price.get(position)));
        holder.quan_txt.setText(String.valueOf(quan.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("fname", String.valueOf(food.get(position)));
                intent.putExtra("price", String.valueOf(price.get(position)));
                intent.putExtra("quan", String.valueOf(quan.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt, fname_txt, price_txt, quan_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id_txt);
            fname_txt = itemView.findViewById(R.id.food_txt);
            price_txt = itemView.findViewById(R.id.price_txt);
            quan_txt = itemView.findViewById(R.id.quantity_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
