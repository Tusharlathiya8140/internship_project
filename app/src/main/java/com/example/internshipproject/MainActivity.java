package com.example.internshipproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

    Button signin;
    EditText email,password;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    TextView forgotPassword,createAccount;
    ImageView hideIv,showIv;

    SQLiteDatabase db;

    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences(ConstantSP.PREF,MODE_PRIVATE);

        db=openOrCreateDatabase("internshipproject.db",MODE_PRIVATE,null);
        String tableQuery = "CREATE TABLE IF NOT EXISTS USERS(USERID INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR(50), EMAIL VARCHAR(50), CONTACT BIGINT(10), PASSWORD VARCHAR(20))";
        db.execSQL(tableQuery);

        signin = findViewById(R.id.main_signin);
        email = findViewById(R.id.main_email);
        password = findViewById(R.id.main_password);
        forgotPassword = findViewById(R.id.main_forgotpassword);
        createAccount = findViewById(R.id.main_createaccount);

        hideIv = findViewById(R.id.main_password_hide);
        showIv = findViewById(R.id.main_password_show);

        hideIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideIv.setVisibility(View.GONE);
                showIv.setVisibility(View.VISIBLE);
                password.setTransformationMethod(null);
            }
        });

        showIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showIv.setVisibility(View.GONE);
                hideIv.setVisibility(View.VISIBLE);
                password.setTransformationMethod(new PasswordTransformationMethod());
            }
        });

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
                    String selectQuery = "SELECT * FROM USERS WHERE (EMAIL = '"+email.getText().toString()+"' OR CONTACT = '"+email.getText().toString()+"') AND PASSWORD = '"+password.getText().toString()+"'";
                    Cursor cursor = db.rawQuery(selectQuery,null);
                    if(cursor.getCount()>0){
                        while (cursor.moveToNext()){
                            sp.edit().putString(ConstantSP.USERID,cursor.getString(0)).commit();
                            sp.edit().putString(ConstantSP.NAME,cursor.getString(1)).commit();
                            sp.edit().putString(ConstantSP.EMAIL,cursor.getString(2)).commit();
                            sp.edit().putString(ConstantSP.CONTACT,cursor.getString(3)).commit();
                            sp.edit().putString(ConstantSP.PASSWORD,cursor.getString(4)).commit();
                        }

                        System.out.print("Login Successfully");
                        Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
//                      Snackbar.make(v,"Login Successfully",Snackbar.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, dashboard.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Invalid EmailId/Password.", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}