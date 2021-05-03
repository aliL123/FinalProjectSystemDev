package com.example.sysdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginAdminActivity extends AppCompatActivity  {

    EditText username, password;
    Button login;
    ImageButton goBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        username = findViewById(R.id.username);
        password = findViewById(R.id.editTextPassword);
        login = findViewById(R.id.loginButton);
        goBack = findViewById(R.id.goBack);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAdminActivity.this, MainActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if(user.isEmpty()){
                    Toast.makeText(LoginAdminActivity.this, "Please enter a username", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(pass.isEmpty()){
                    Toast.makeText(LoginAdminActivity.this, "Please enter a password", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });

    }
}