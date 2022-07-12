package com.project.belajarmengaji;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread thread = new Thread(){
            public  void  run(){
                try{
                    sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(SplashActivity.this, LoginSiswaActivity.class));
                    finish();
                }
            }
        };
        thread.start();
    }
}
