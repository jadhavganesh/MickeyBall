package com.crossinfinity.sh.mickeyball;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    Button play,score,options,about;
    int backcount=0;
    FloatingActionButton voice, voice1,option;
    static boolean sflag = false;
    SharedPreferences prefs ;
    SharedPreferences.Editor editor;
    CountDownTimer ct;
    int c=0;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        play=(Button)findViewById(R.id.button);
        score=(Button)findViewById(R.id.button2);
        about=(Button)findViewById(R.id.button3);
        prefs = getSharedPreferences("hs", MODE_PRIVATE);
        editor = getSharedPreferences("hs", MODE_PRIVATE).edit();
        voice = (FloatingActionButton) findViewById(R.id.floatingActionButton7);
        voice1 = (FloatingActionButton) findViewById(R.id.floatingActionButton9);
        option = (FloatingActionButton) findViewById(R.id.floatingActionButton10);
        voice1.setVisibility(View.VISIBLE);
        voice.setVisibility(View.INVISIBLE);


        viewmickey();
        sflag =  prefs.getBoolean("sound", true);
        if(sflag)
        {
            mp = MediaPlayer.create(getApplicationContext(), R.raw.bg);
            mp.start();
            voice.setVisibility(View.VISIBLE);
            voice1.setVisibility(View.INVISIBLE);
        }
        else
        {
            voice1.setVisibility(View.VISIBLE);
            voice.setVisibility(View.INVISIBLE);
        }

        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                sflag = false;
                voice1.setVisibility(View.VISIBLE);
                voice.setVisibility(View.INVISIBLE);
                editor.putBoolean("sound", sflag);    editor.commit();




            }
        });

        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sflag)
                {mp.stop();}
                finish();
                Intent i=new Intent(getApplicationContext(),Start.class);
                startActivity(i);
            }
        });

        voice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.bg);
                mp.start();

                sflag = true;
                voice.setVisibility(View.VISIBLE);
                voice1.setVisibility(View.INVISIBLE);
                editor.putBoolean("sound", sflag);    editor.commit();
            }
        });

            final int hscore = prefs.getInt("hscore", 0);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sflag){mp.stop();}
                finish();
                Intent gameplay=new Intent(getApplicationContext(),Gameplay.class);
                startActivity(gameplay);
            }
        });



       about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sflag){mp.stop();}
                finish();
                Intent gameplay=new Intent(getApplicationContext(),AboutUs.class);
                startActivity(gameplay);

            }
        });

        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder build = new AlertDialog.Builder(Home.this);
                build.setTitle("Score");
                build.setMessage("Highscore : "+String.valueOf(hscore));
                build.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog ad = build.create();

                ad.show();
            }
        });


    }



    @Override
    public void onBackPressed() {


        backcount++;
        if(backcount==2)
        {
            if(sflag){
            mp.stop();}
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Press again for exit",Toast.LENGTH_SHORT).show();
        }
    }



    public void viewmickey()
    {
        ct= new CountDownTimer(500, 500) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                if(c==1)
                {
                    PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1, 1.2f);
                    PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1, 1.2f);
                    ObjectAnimator scaleAnimation = ObjectAnimator.ofPropertyValuesHolder(play, pvhX, pvhY);

                    AnimatorSet setAnimation = new AnimatorSet();
                    setAnimation.play(scaleAnimation);
                    setAnimation.start();

                }
                else
                {
                    PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1, 1f);
                    PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1, 1f);
                    ObjectAnimator scaleAnimation = ObjectAnimator.ofPropertyValuesHolder(play, pvhX, pvhY);

                    AnimatorSet setAnimation = new AnimatorSet();
                    setAnimation.play(scaleAnimation);
                    setAnimation.start();


                }

            }

            public void onFinish() {

                if(c==1)
                {
                    c=0;
                    viewmickey();
                }
                else
                {
                    c=1;
                    viewmickey();
                }
            }
        };

        ct.start();
    }



    }

