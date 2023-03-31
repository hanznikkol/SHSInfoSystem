package com.example.shsinfosystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class StudentInfoActivity extends AppCompatActivity {
    EditText edFirstName, edMiddleInitial, edLastName, edMobileNum, edAge, edAddress;
    EditText edYearLevel, edStrand;
    RadioGroup genderRadio;
    Button saveBtn;
    DBHelper dbHelper;
    int studentId;
    StudentItem studentInfo;
    RadioButton radioButton;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save);

        dbHelper = new DBHelper(this);
        Bundle intent = getIntent().getExtras();
        studentId = intent.getInt("student_id",-1);
        edFirstName = (EditText) findViewById(R.id.edFirstName);
        edMiddleInitial =(EditText) findViewById(R.id.edMiddleInitial);
        edLastName =(EditText) findViewById(R.id.edLastName);
        edYearLevel = (EditText) findViewById(R.id.edYearLevel);
        edStrand= (EditText) findViewById(R.id.edStrand);
        edMobileNum = (EditText)findViewById(R.id.edMobileNum);
        edAge =(EditText)findViewById(R.id.edAge);
        edAddress = (EditText)findViewById(R.id.edAddress);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        genderRadio = (RadioGroup) findViewById(R.id.edGender);
        studentInfo = dbHelper.getStudentInfo(studentId);

        edFirstName.setText(studentInfo.firstName);
        edMiddleInitial.setText(studentInfo.middleInitial);
        edLastName.setText(studentInfo.lastName);
        edYearLevel.setText(String.valueOf(studentInfo.yearLevel));
        edStrand.setText(studentInfo.strand);
        edMobileNum.setText(String.valueOf(studentInfo.mobileNum));
        edAge.setText(String.valueOf(studentInfo.age));
        edAddress.setText(studentInfo.address);

        if(studentInfo.gender.equals("Male"))
            ((RadioButton)genderRadio.getChildAt(0)).setChecked(true);
        else
            ((RadioButton)genderRadio.getChildAt(1)).setChecked(true);

        genderRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = genderRadio.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(id);
                gender = radioButton.getText().toString();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean updateStudent = dbHelper.updateStudent(studentId, edFirstName.getText().toString(), edMiddleInitial.getText().toString(), edLastName.getText().toString(),
                        Integer.parseInt(edYearLevel.getText().toString()), edStrand.getText().toString(), edMobileNum.getText().toString(), gender,
                        Integer.parseInt(edAge.getText().toString()), edAddress.getText().toString());

                if(updateStudent){
                    Toast.makeText(StudentInfoActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        });

    }
    public void checkButton(View v){
        int id = genderRadio.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(id);
        gender = radioButton.getText().toString();
        Toast.makeText(StudentInfoActivity.this, gender, Toast.LENGTH_SHORT).show();
    }
}