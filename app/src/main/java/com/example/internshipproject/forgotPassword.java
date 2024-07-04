package com.example.internshipproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class forgotPassword extends AppCompatActivity {

    Button changePassword;
    EditText email,password,confrimPassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        changePassword = findViewById(R.id.change_password);
        email = findViewById(R.id.f_email);
        password = findViewById(R.id.f_password);
        confrimPassword = findViewById(R.id.f_confrim_password);

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().trim().equals("")){
                    email.setError("Email ID Required");
                }
                else if (!email.getText().toString().trim().matches(emailPattern)) {
                    email.setError("valid Email ID Required");
                }
                else if (password.getText().toString().trim().equals("")) {
                    password.setError("password ID Required");
                }
                else if (password.getText().toString().trim().length()<6) {
                    password.setError("Min. 6 Char password required");
                }
                else if (confrimPassword.getText().toString().trim().equals("")) {
                    confrimPassword.setError("password ID Required");
                }
                else if (confrimPassword.getText().toString().trim().length()<6) {
                    confrimPassword.setError("Min. 6 Char password required");
                }
                else if (!confrimPassword.getText().toString().trim().matches(password.getText().toString().trim())) {
                    confrimPassword.setError("password not same");
                }
                else {
                    Toast.makeText(forgotPassword.this, "Password Changed", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });




    }
}