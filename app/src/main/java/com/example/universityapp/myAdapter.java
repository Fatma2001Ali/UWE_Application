package com.example.universityapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {

    Context context;
    ArrayList<userdata> list;

    public myAdapter(Context context, ArrayList<userdata> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item , parent , false);
        return  new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        userdata userdata = list.get(position);
        holder.first_name.setText(userdata.getFirstName());
//        holder.last_name.setText(userdata.getLastName());
        holder.age_.setText(userdata.getAge());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class  myViewHolder extends RecyclerView.ViewHolder{

        TextView first_name , last_name , age_;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            first_name = itemView.findViewById(R.id.firstname);
//            last_name = itemView.findViewById(R.id.lastname);
            age_ = itemView.findViewById(R.id.age);
        }
    }
}
