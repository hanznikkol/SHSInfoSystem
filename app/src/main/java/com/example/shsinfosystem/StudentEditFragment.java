package com.example.shsinfosystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class StudentEditFragment extends Fragment {
    EditText edFirstName, edMiddleInitial, edLastName, edMobileNum, edAge, edAddress;
    EditText edYearLevel, edStrand;
    RadioGroup genderRadio;
    Button saveBtn;
    DBHelper dbHelper;
    int studentId;
    StudentItem studentInfo;
    String gender;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.save, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbHelper = new DBHelper(getActivity());
        Bundle bundle = getArguments();
        if(bundle != null)
            studentId = bundle.getInt("student_id",-1);
        edFirstName = (EditText) getView().findViewById(R.id.edFirstName);
        edMiddleInitial =(EditText) getView().findViewById(R.id.edMiddleInitial);
        edLastName =(EditText) getView().findViewById(R.id.edLastName);
        edYearLevel = (EditText) getView().findViewById(R.id.edYearLevel);
        edStrand= (EditText) getView().findViewById(R.id.edStrand);
        edMobileNum = (EditText)getView().findViewById(R.id.edMobileNum);
        edAge =(EditText)getView().findViewById(R.id.edAge);
        edAddress = (EditText)getView().findViewById(R.id.edAddress);
        saveBtn = (Button) getView().findViewById(R.id.saveBtn);
        genderRadio = (RadioGroup) getView().findViewById(R.id.edGender);
        studentInfo = dbHelper.getStudentInfo(studentId);

        edFirstName.setText(studentInfo.firstName);
        edMiddleInitial.setText(studentInfo.middleInitial);
        edLastName.setText(studentInfo.lastName);
        edYearLevel.setText(String.valueOf(studentInfo.yearLevel));
        edStrand.setText(studentInfo.strand);
        edMobileNum.setText(String.valueOf(studentInfo.mobileNum));
        edAge.setText(String.valueOf(studentInfo.age));
        edAddress.setText(studentInfo.address);
        gender = studentInfo.gender;
        if(studentInfo.gender.equals("Male"))
            ((RadioButton)genderRadio.getChildAt(0)).setChecked(true);
        else
            ((RadioButton)genderRadio.getChildAt(1)).setChecked(true);

        ((RadioButton)genderRadio.getChildAt(0)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "Male";
                Toast.makeText(getActivity(), gender, Toast.LENGTH_SHORT).show();
            }
        });
        ((RadioButton)genderRadio.getChildAt(1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "Female";
                Toast.makeText(getActivity(), gender, Toast.LENGTH_SHORT).show();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean updateStudent = dbHelper.updateStudent(studentId, edFirstName.getText().toString(), edMiddleInitial.getText().toString(), edLastName.getText().toString(),
                        Integer.parseInt(edYearLevel.getText().toString()), edStrand.getText().toString(), edMobileNum.getText().toString(), gender,
                        Integer.parseInt(edAge.getText().toString()), edAddress.getText().toString());

                if(updateStudent){
                    Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();

                }
            }
        });
    }


}