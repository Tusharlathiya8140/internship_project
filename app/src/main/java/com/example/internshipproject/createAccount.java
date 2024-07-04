package com.example.internshipproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class createAccount extends AppCompatActivity {

    Button signup;
    EditText email,password,confrimPassword,contact,name;
    TextView alrady_account;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        signup = findViewById(R.id.signup);
        name = findViewById(R.id.signup_name);
        contact = findViewById(R.id.signup_contact);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        confrimPassword = findViewById(R.id.signup_confirm_password);
        alrady_account = findViewById(R.id.signup_already_account);

        alrady_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().trim().equals("")){
                    name.setError("Name Required");
                }
                else if (contact.getText().toString().trim().equals("")) {
                    contact.setError("Contact number Required");
                }
                else if (contact.getText().toString().trim().length()<10){
                    contact.setError("Invalid Contact");
                }
                else if(email.getText().toString().trim().equals("")){
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
                    Toast.makeText(createAccount.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });

    }
}