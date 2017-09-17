package com.crossinfinity.sh.mickeyball;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;


public class Start extends AppCompatActivity {

    int backcount = 0, c = 0, count = 0;
    FloatingActionButton voice, voice1, share, infom;
    static boolean sflag = false;
    static String str, str2;
    Spinner sp, sp2;
    String[] time = {"10", "20", "30", "40", "50", "60"};
    String[] mode = {"Easy", "Medium", "Hard", "Expert"};
    TextView mickey;
    RelativeLayout rl;
    CountDownTimer ct;
    SharedPreferences prefs ;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        prefs = getSharedPreferences("hs", MODE_PRIVATE);
        editor = getSharedPreferences("hs", MODE_PRIVATE).edit();

        infom = (FloatingActionButton) findViewById(R.id.floatingActionButton11);


        rl = (RelativeLayout) findViewById(R.id.content_start);

        sp2 = (Spinner) findViewById(R.id.spinner2);



        ArrayAdapter aa1 = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, time);
        aa1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp2.setAdapter(aa1);

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str = time[position];
                editor.putString("time", str);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        infom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder build = new AlertDialog.Builder(Start.this);
                build.setTitle("How to Play?");
                build.setMessage("1) Click on floating balls and get points\n2) Ball Acquires Random position\n3) Clicking outside the ball's boundary makes your points minus \n\n             ALL THE BEST ;) ");
                build.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog ad = build.create();

                ad.show();


            }
        });



       /* do {
            new CountDownTimer(2000, 1000) { // adjust the milli seconds here

                public void onTick(long millisUntilFinished) {

                    mickey.setVisibility(View.VISIBLE);
                }

                public void onFinish() {
                    mickey.setVisibility(View.INVISIBLE);

                }

            }.start();

        }while(true);*/


    }
    static String value2()
    {
        return  "Easy";
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(Start.this,Home.class);
        startActivity(i);


    }
}



