package com.example.shsinfosystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    EditText edFirstName, edMiddleInitial, edLastName, edMobileNum, edAge, edAddress;
    EditText edYearLevel, edStrand;
    RadioGroup genderRadio;
    Button enrollBtn;
    int studentId;
    DBHelper dbHelper;
    StudentItem studentInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enroll);
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
        enrollBtn = (Button) findViewById(R.id.enrollBtn);
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




        enrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int accnum = dbHelper.generateAccountNumber(studentId);
                String password = dbHelper.generateCode();
                boolean enroll = dbHelper.enrollStudent(studentId, accnum, password);

                if(enroll){
                    new AlertDialog.Builder(MainActivity2.this)
                            .setTitle("Enrollment")
                            .setMessage("Account Number: "+accnum + "\nPassword: "  + password)

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })

                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                }
            }
        });

    }


}