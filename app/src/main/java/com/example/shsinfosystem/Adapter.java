package com.example.shsinfosystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements View.OnClickListener {

    Context context;
    List<Item> item;

    public Adapter(Context context, List<Item> item) {
        this.context = context;
        this.item = item;
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


        if(context instanceof  UnenrolledActivity) {

            int position = ((UnenrolledActivity) context).recyclerView.getChildLayoutPosition(view);
            Intent intent = new Intent(context, MainActivity2.class);
            intent.putExtra("student_id", item.get(position).id);
            ((UnenrolledActivity) context).startActivity(intent);
        }
        if(context instanceof StudentViewActivity){
            int position = ((StudentViewActivity) context).recyclerView.getChildLayoutPosition(view);
            Intent intent = new Intent(context, StudentInfoActivity.class);
            intent.putExtra("student_id", item.get(position).id);
            ((StudentViewActivity) context).startActivity(intent);
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
