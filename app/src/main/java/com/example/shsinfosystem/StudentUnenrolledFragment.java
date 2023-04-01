package com.example.shsinfosystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class StudentUnenrolledFragment extends Fragment {

    EditText edFirstName, edMiddleInitial, edLastName, edMobileNum, edAge, edAddress;
    EditText edYearLevel, edStrand;
    RadioGroup genderRadio;
    Button enrollBtn;
    int studentId;
    DBHelper dbHelper;
    StudentItem studentInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.enroll, container, false);
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
        enrollBtn = (Button) getView().findViewById(R.id.enrollBtn);
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
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Enrollment")
                            .setMessage("Account Number: "+accnum + "\nPassword: "  + password)

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    getActivity().onBackPressed();
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


}