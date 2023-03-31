package com.example.shsinfosystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

public class UnenrolledActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DBHelper dbHelper;
    List<Item> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unenrolled);
        dbHelper = new DBHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.studentView);

        lista = dbHelper.getUnenrolledStudents();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(this, lista));

        

    }
}