package com.example.internshipproject;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    Button submit,edit;
    EditText email,password,confrimPassword,contact,name;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    SharedPreferences sp;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sp = getSharedPreferences(ConstantSP.PREF,MODE_PRIVATE);

        db=openOrCreateDatabase("internshipproject.db",MODE_PRIVATE,null);
        String tableQuery = "CREATE TABLE IF NOT EXISTS USERS(USERID INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR(50), EMAIL VARCHAR(50), CONTACT BIGINT(10), PASSWORD VARCHAR(20))";
        db.execSQL(tableQuery);

        submit = findViewById(R.id.submit);
        edit = findViewById(R.id.edit_profile);
        name = findViewById(R.id.profile_name);
        contact = findViewById(R.id.profile_contact);
        email = findViewById(R.id.profile_email);
        password = findViewById(R.id.profile_password);
        confrimPassword = findViewById(R.id.profile_confirm_password);


        submit.setOnClickListener(new View.OnClickListener() {
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
                    String selectQuery = "SELECT * FROM USERS WHERE USERID = '"+sp.getString(ConstantSP.USERID,"")+"'";
                    Cursor cursor = db.rawQuery(selectQuery,null);
                    if(cursor.getCount()>0){
                        String updateQueary = "UPDATE USERS SET NAME='"+name.getText().toString()+"', EMAIL ='"+email.getText().toString()+"', CONTACT='"+contact.getText().toString()+"', PASSWORD='"+password.getText().toString()+"' WHERE USERID='"+sp.getString(ConstantSP.USERID,"")+"'";
                        db.execSQL(updateQueary);

                        sp.edit().putString(ConstantSP.NAME,name.getText().toString()).commit();
                        sp.edit().putString(ConstantSP.EMAIL,email.getText().toString()).commit();
                        sp.edit().putString(ConstantSP.CONTACT,contact.getText().toString()).commit();
                        sp.edit().putString(ConstantSP.PASSWORD,password.getText().toString()).commit();
                        setData(false);
                    }
                    else {
                        Toast.makeText(ProfileActivity.this,"Invalid UserID", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        setData(false);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setData(true);
            }
        });
    }

    private void setData(boolean b) {
        name.setText(sp.getString(ConstantSP.NAME,""));
        email.setText(sp.getString(ConstantSP.EMAIL,""));
        contact.setText(sp.getString(ConstantSP.CONTACT,""));
        password.setText(sp.getString(ConstantSP.PASSWORD,""));
        confrimPassword.setText(sp.getString(ConstantSP.PASSWORD,""));

        name.setEnabled(b);
        email.setEnabled(b);
        contact.setEnabled(b);
        password.setEnabled(b);
        confrimPassword.setEnabled(b);

        if(b){
            confrimPassword.setVisibility(View.VISIBLE);
            edit.setVisibility(View.GONE);
            submit.setVisibility(View.VISIBLE);
        }
        else{
            confrimPassword.setVisibility(View.GONE);
            edit.setVisibility(View.VISIBLE);
            submit.setVisibility(View.GONE);
        }

    }
}