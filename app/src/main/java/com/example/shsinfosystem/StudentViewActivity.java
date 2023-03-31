package com.example.shsinfosystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class StudentViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelper dbHelper;
    List<Item>lista;
    FloatingActionButton unenrolledBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students);
        dbHelper = new DBHelper(this);
        recyclerView = findViewById(R.id.studentView);
        unenrolledBtn = (FloatingActionButton) findViewById(R.id.unenrolledBtn);
        lista = dbHelper.getEnrolledStudents();
        unenrolledBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentViewActivity.this, UnenrolledActivity.class));
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(this, lista));

    }


}
