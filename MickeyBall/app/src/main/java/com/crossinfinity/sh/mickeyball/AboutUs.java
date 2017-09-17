package com.crossinfinity.sh.mickeyball;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class AboutUs extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent gameplay=new Intent(getApplicationContext(),Home.class);
        startActivity(gameplay);

    }
}
