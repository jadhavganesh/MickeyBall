package com.crossinfinity.sh.mickeyball;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences.Editor editor = getSharedPreferences("hs", MODE_PRIVATE).edit();
        editor.putString("time","10");
        editor.commit();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread mythread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent in=new Intent(getApplicationContext(),Home.class);
                    startActivity(in);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        mythread.start();
    }

}
