package com.mariaeduarda.meucopo.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.mariaeduarda.meucopo.R;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    public static final int TIME_OUT_SPLASH =2000;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        comutartelaSplash();

    }
    private void comutartelaSplash(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent telaPrincipal = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        } ,  TIME_OUT_SPLASH);
    }

}
