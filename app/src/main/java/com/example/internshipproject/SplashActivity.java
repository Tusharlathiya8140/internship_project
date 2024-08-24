package com.example.internshipproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sp = getSharedPreferences(ConstantSP.PREF,MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sp.getString(ConstantSP.USERID,"").equalsIgnoreCase("")){
                    Intent intent = new Intent(SplashActivity.this , MainActivity.class);
                    startActivity(intent);

                }
                else{
                    Intent intent = new Intent(SplashActivity.this , dashboard.class);
                    startActivity(intent);

                }
            }
        },0);


    }
}