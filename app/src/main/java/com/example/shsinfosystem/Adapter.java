package com.example.shsinfosystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements View.OnClickListener {

    Context context;
    List<Item> item;
    Fragment frag;
    public Adapter(Context context, Fragment frag, List<Item> item) {
        this.context = context;
        this.item = item;
        this.frag = frag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.fullName.setText(item.get(position).fullName);
        holder.yearLevel.setText("Year " + String.valueOf(item.get(position).yearLevel));
        holder.accNo.setText(String.valueOf(item.get(position).accNo));
        holder.img.setImageResource(item.get(position).image);
    }

    @Override
    public void onClick(View view) {


        if(frag instanceof UnenrolledFragment) {

            int position = ((UnenrolledFragment) frag).recyclerView.getChildLayoutPosition(view);
            Bundle bundle = new Bundle();
            bundle.putInt("student_id", item.get(position).id);
            StudentUnenrolledFragment fragment = new StudentUnenrolledFragment();
            fragment.setArguments(bundle);
            ((StudentViewActivity) context).replaceFragment(fragment);

        }
        else if(context instanceof StudentViewActivity){
            int position = ((StudentViewActivity) context).recyclerView.getChildLayoutPosition(view);
            Bundle bundle = new Bundle();
            bundle.putInt("student_id", item.get(position).id);
            StudentEditFragment fragment = new StudentEditFragment();
            fragment.setArguments(bundle);
            ((StudentViewActivity)context).replaceFragment(fragment);
        }


    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        public TextView fullName, yearLevel, accNo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img= itemView.findViewById(R.id.imageView);
            fullName= itemView.findViewById(R.id.fullName);
            yearLevel= itemView.findViewById(R.id.yearLevel);
            accNo= itemView.findViewById(R.id.accNumber);
        }


    }

    @Override
    public int getItemCount() {
        return item.size();
    }


}
