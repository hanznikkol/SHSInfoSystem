package com.example.shsinfosystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
                replaceFragment(new UnenrolledFragment());
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(this, null, lista));

    }

    public void replaceFragment(Fragment fragment){
        String tag = fragment.getClass().getSimpleName();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, 0,0,R.anim.exit_from_right);
        ft.add(R.id.full_fragment_layout, fragment, tag);
        ft.addToBackStack(tag);
        ft.commit();

    }



}
