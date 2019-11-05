package com.dwputuyudhiardiana.cecimpedan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private Intent activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed
                (new Runnable() {
                    @Override
                    public void run()
                    {
                            activity = new Intent(SplashActivity.this,
                                    LoginActivity.class);
                        startActivity(activity);
                        finish();
                    }
                }, SPLASH_TIME_OUT);
    }
}
