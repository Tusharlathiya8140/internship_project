package com.example.internshipproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

    Button signin;
    EditText email,password;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signin = findViewById(R.id.main_signin);
        email = findViewById(R.id.main_email);
        password = findViewById(R.id.main_password);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().trim().equals("")){
                    email.setError("Email ID Required");
                }
                else if (!email.getText().toString().trim().matches(emailPattern)) {
                    email.setError("valid Email ID Required");
                }
                else if (password.getText().toString().trim().equals("")) {
                    password.setError("Email ID Required");
                }
                else if (password.getText().toString().trim().length()<6) {
                    password.setError("Min. 6 Char password required");
                }
                else {
                    System.out.print("Login Successfully");
                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Snackbar.make(view,"Login Successfully",Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}