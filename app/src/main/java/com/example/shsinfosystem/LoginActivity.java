package com.example.shsinfosystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText edAccountNum, edPassword;
    TextView registerBtn;
    Button loginBtn;

    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        edAccountNum = (EditText) findViewById(R.id.edAccountNo);
        edPassword = (EditText) findViewById(R.id.edPassword);

        registerBtn = (TextView) findViewById(R.id.registerBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn) ;
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userId = dbHelper.getUserId(edAccountNum.getText().toString(), edPassword.getText().toString());

                if(userId > 0){
                    //startActivity(new Intent(LoginActivity.this, StudentViewActivity.class));
                }
                else if(edAccountNum.getText().toString().equals("admin") && edPassword.getText().toString().equals("admin")){

                    startActivity(new Intent(LoginActivity.this, StudentViewActivity.class));
                }
                else if(edAccountNum.getText().toString().equals("teacher") && edPassword.getText().toString().equals("teacher")){

                    startActivity(new Intent(LoginActivity.this, StudentViewActivity.class));
                }



            }
        });

    }
}