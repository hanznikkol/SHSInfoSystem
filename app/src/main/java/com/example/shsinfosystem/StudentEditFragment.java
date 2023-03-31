package com.example.shsinfosystem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class StudentEditFragment extends Fragment {
    EditText edFirstName, edMiddleInitial, edLastName, edYearLevel, edStrand, edMobileNum, edAge, edAddress;
    RadioGroup genderRadio;
    Button saveBtn;
    DBHelper dbHelper;
    StudentItem studentInfo;
    int studentId;
    public StudentEditFragment() {
        super(0);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_student_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbHelper = new DBHelper(getActivity());
        Bundle bundle = getArguments();
        studentId = bundle.getInt("student_id");
        edFirstName = (EditText) view.findViewById(0);
        edMiddleInitial =(EditText) view.findViewById(0);
        edLastName =(EditText) view.findViewById(0);
        edYearLevel = (EditText)view.findViewById(0);
        edStrand= (EditText)view.findViewById(0);
        edMobileNum = (EditText)view.findViewById(0);
        edAge =(EditText) view.findViewById(0);
        edAddress = (EditText)view.findViewById(0);
        saveBtn = (Button) view.findViewById(0);

        studentInfo = dbHelper.getStudentInfo(studentId);

        edFirstName.setText(studentInfo.firstName);
        edMiddleInitial.setText(studentInfo.middleInitial);
        edLastName.setText(studentInfo.lastName);
        edYearLevel.setText(String.valueOf(studentInfo.yearLevel));
        edStrand.setText(studentInfo.strand);
        edMobileNum.setText(String.valueOf(studentInfo.mobileNum));
        edAge.setText(String.valueOf(studentInfo.age));
        edAddress.setText(studentInfo.address);

    }
}