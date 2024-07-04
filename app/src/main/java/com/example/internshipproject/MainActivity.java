package com.example.internshipproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

    Button signin;
    EditText email,password;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    TextView forgotPassword,createAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signin = findViewById(R.id.main_signin);
        email = findViewById(R.id.main_email);
        password = findViewById(R.id.main_password);
        forgotPassword = findViewById(R.id.main_forgotpassword);
        createAccount = findViewById(R.id.main_createaccount);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,forgotPassword.class);
                startActivity(intent);
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,createAccount.class);
                startActivity(intent);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
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
                else {
                    System.out.print("Login Successfully");
                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Snackbar.make(v,"Login Successfully",Snackbar.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,DashboatdActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}