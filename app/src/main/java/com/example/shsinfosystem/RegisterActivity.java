package com.example.shsinfosystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    EditText edFirstName, edMiddleInitial, edLastName, edMobileNum, edAge, edAddress;
    Spinner edYearLevel, edStrand;
    RadioGroup genderRadio;
    Button registerBtn;
    DBHelper dbHelper;

    String year;
    String strand;
    String gender;
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        dbHelper = new DBHelper(this);
        edFirstName = (EditText) findViewById(R.id.edFirstName);
        edMiddleInitial= (EditText) findViewById(R.id.edMiddleInitial);
        edLastName = (EditText) findViewById(R.id.edLastName);
        edYearLevel = (Spinner) findViewById(R.id.edYearLevel);
        edStrand = (Spinner) findViewById(R.id.edStrand);
        edMobileNum = (EditText) findViewById(R.id.edMobileNum);
        edAge = (EditText) findViewById(R.id.edAge);
        edAddress = (EditText) findViewById(R.id.edAddress);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        genderRadio = (RadioGroup) findViewById(R.id.edGender);


        String[] years = getResources().getStringArray(R.array.years);
        String[] strands = getResources().getStringArray(R.array.strands);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, years);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edYearLevel.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, strands);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edStrand.setAdapter(adapter2);


        edYearLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                year = years[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edStrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                strand = strands[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                boolean insert = dbHelper.registerStudent(edFirstName.getText().toString(), edMiddleInitial.getText().toString(), edLastName.getText().toString(),
                        Integer.parseInt(year), strand, edMobileNum.getText().toString(),gender,
                        Integer.parseInt(edAge.getText().toString()), edAddress.getText().toString());

                if(insert) {
                    Toast.makeText(RegisterActivity.this, "Registered!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });


    }
    public void checkButton(View v){
        int id = genderRadio.getCheckedRadioButtonId();
        radioButton = findViewById(id);
        gender = radioButton.getText().toString();
        Toast.makeText(RegisterActivity.this, gender, Toast.LENGTH_SHORT).show();
    }



}