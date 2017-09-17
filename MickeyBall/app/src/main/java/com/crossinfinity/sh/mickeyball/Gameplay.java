package com.crossinfinity.sh.mickeyball;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class Gameplay extends AppCompatActivity {


    FloatingActionButton fa0, fa1, fa2, fa3, fa4;
    static TextView counter,timer,gameover,score;
    Random r = new Random();
    RelativeLayout rl;
    int count = 0, i ,max=10, bg0 = 0;
    Boolean mv=true;
    int i1=5,i2=20,i3=40,i4=70;
    ProgressBar pb;
    int x,y,c=0;
    int[] loc = new int[2];
    MediaPlayer mp,mp1;
    CountDownTimer ct,ct2;
    Button restart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        AlertDialog.Builder build = new AlertDialog.Builder(Gameplay.this);
        build.setIcon(R.drawable.mickeyplay);
        build.setTitle("Find & Click on Mickey Ball...\n Enjoy :)");
        build.setPositiveButton("Start Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog ad = build.create();

        ad.show();


        final SharedPreferences prefs = getSharedPreferences("hs", MODE_PRIVATE);

            max = Integer.parseInt(prefs.getString("time", null));


        fa0 = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fa1 = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        fa2 = (FloatingActionButton) findViewById(R.id.floatingActionButton3);
        fa3 = (FloatingActionButton) findViewById(R.id.floatingActionButton4);
        fa4 = (FloatingActionButton) findViewById(R.id.floatingActionButton5);



        rl = (RelativeLayout) findViewById(R.id.content_gameplay);
        counter = (TextView) findViewById(R.id.textView);
        timer = (TextView) findViewById(R.id.textView5);
        gameover=(TextView) findViewById(R.id.textView9);
        score=(TextView) findViewById(R.id.textView2);
        restart = (Button) findViewById(R.id.button);


        pb = (ProgressBar) findViewById(R.id.progressBar3);

        score.setVisibility(View.INVISIBLE);
        gameover.setVisibility(View.INVISIBLE);
        fa2.setVisibility(View.INVISIBLE);
        fa3.setVisibility(View.INVISIBLE);
        fa4.setVisibility(View.INVISIBLE);
        fa1.setVisibility(View.INVISIBLE);
        restart.setVisibility(View.INVISIBLE);



        fa0.getLocationOnScreen(loc);
        x = loc[0];
        y = loc[1];
        pb.setMax(max);

        timer.setText(String.valueOf(max) + " sec");
        mp = MediaPlayer.create(getApplicationContext(), R.raw.bg);
        mp1 = MediaPlayer.create(getApplicationContext(), R.raw.beep);

        fa0.setTranslationX(r.nextInt(10000) % (rl.getWidth() - 250));
        fa0.setTranslationY(r.nextInt(10000) % (rl.getHeight() - 250));
        fa1.setTranslationX(r.nextInt(10000) % (rl.getWidth() - 250));
        fa1.setTranslationY(r.nextInt(10000) % (rl.getHeight() - 250));
        fa2.setTranslationX(r.nextInt(10000) % (rl.getWidth() - 250));
        fa2.setTranslationY(r.nextInt(10000) % (rl.getHeight() - 250));
        fa3.setTranslationX(r.nextInt(10000) % (rl.getWidth() - 250));
        fa3.setTranslationY(r.nextInt(10000) % (rl.getHeight() - 250));
        fa4.setTranslationX(r.nextInt(10000) % (rl.getWidth() - 250));
        fa4.setTranslationY(r.nextInt(10000) % (rl.getHeight() - 250));


        if(prefs.getBoolean("sound",false)) {
            mp.start();
        }

        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=count-2;
                counter.setText(String.valueOf(count));
                Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vib.vibrate(200);

            }
        });
        fa0.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mv=false;

                mp1.start();
                if (bg0 == 0)

                {
                    counter.setVisibility(View.VISIBLE);
                    ct=new CountDownTimer(max * 1000, 1000) { // adjust the milli seconds here

                        public void onTick(long millisUntilFinished) {
                            timer.setText(String.valueOf(millisUntilFinished / 1000) + " sec");
                            pb.setProgress((int) millisUntilFinished / 1000);
                        }

                        public void onFinish() {
                            int mode=max/10;
                            int s=Integer.parseInt(Gameplay.value())/mode;
                           score.setText("Score : "+s);
                            SharedPreferences prefs = getSharedPreferences("hs", MODE_PRIVATE);
                            final int hscore = prefs.getInt("hscore", 0);
                            if(s>hscore) {
                                SharedPreferences.Editor editor = getSharedPreferences("hs", MODE_PRIVATE).edit();
                                editor.putInt("hscore",s);
                                editor.commit();
                            }
                            fa0.setVisibility(View.INVISIBLE);
                            fa1.setVisibility(View.INVISIBLE);
                            fa2.setVisibility(View.INVISIBLE);
                            fa3.setVisibility(View.INVISIBLE);
                            fa4.setVisibility(View.INVISIBLE);

                            gameover.setVisibility(View.VISIBLE);
                            score.setVisibility(View.VISIBLE);
                            restart.setVisibility(View.VISIBLE);

                            mp.stop();
                            counter.setVisibility(View.INVISIBLE);
                            pb.setVisibility(View.INVISIBLE);
                            timer.setVisibility(View.INVISIBLE);
                            count=0;
                            fa0.setX(x+35);
                            fa0.setY(y+35);

                        }
                    }.start();
                }
                fa0.setTranslationX(r.nextInt(10000) % (rl.getWidth() - 250));
                fa0.setTranslationY(r.nextInt(10000) % (rl.getHeight() - 250));
                bg0++;


                count++;
                counter.setText(String.valueOf(count));
                if (count >= i1) {
                    fa1.setVisibility(View.VISIBLE);
                }
                if (count >= i2) {
                    fa2.setVisibility(View.VISIBLE);
                }
                if (count >= i3) {
                    fa3.setVisibility(View.VISIBLE);
                }
                if (count >= i4) {
                    fa4.setVisibility(View.VISIBLE);
                }

            }
        });

        fa1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp1.start();
                fa1.setTranslationX(r.nextInt(10000) % (rl.getWidth() - 200));
                fa1.setTranslationY(r.nextInt(10000) % (rl.getHeight() - 250));
                count=count+2;

                counter.setText(String.valueOf(count));
                if (count >= i1) {
                    fa1.setVisibility(View.VISIBLE);

                }
                if (count >= i2) {
                    fa2.setVisibility(View.VISIBLE);
                }
                if (count >= i3) {
                    fa3.setVisibility(View.VISIBLE);
                }
                if (count >= i4) {
                    fa4.setVisibility(View.VISIBLE);
                }
            }
        });

        fa2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp1.start();
                fa2.setTranslationX(r.nextInt(10000) % (rl.getWidth() - 200));
                fa2.setTranslationY(r.nextInt(10000) % (rl.getHeight() - 250));
                count=count+4;

                counter.setText(String.valueOf(count));
                if (count >= i1) {
                    fa1.setVisibility(View.VISIBLE);

                }
                if (count >= i2) {
                    fa2.setVisibility(View.VISIBLE);
                }
                if (count >= i3) {
                    fa3.setVisibility(View.VISIBLE);
                }
                if (count >= i4) {
                    fa4.setVisibility(View.VISIBLE);
                }
            }
        });

        fa3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp1.start();
                fa3.setTranslationX(r.nextInt(10000) % (rl.getWidth() - 200));
                fa3.setTranslationY(r.nextInt(10000) % (rl.getHeight() - 250));
                counter.setText(String.valueOf(count));

                count=count+5;
                if (count >= i1) {
                    fa1.setVisibility(View.VISIBLE);

                }
                if (count >= i2) {
                    fa2.setVisibility(View.VISIBLE);
                }
                if (count >= i3) {
                    fa3.setVisibility(View.VISIBLE);
                }
                if (count >= i4) {
                    fa4.setVisibility(View.VISIBLE);
                }
            }
        });

        fa4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp1.start();
                fa4.setTranslationX(r.nextInt(10000) % (rl.getWidth() - 200));
                fa4.setTranslationY(r.nextInt(10000) % (rl.getHeight() - 250));
                count=count+6;

                counter.setText(String.valueOf(count));
                if (count >= i1) {
                    fa1.setVisibility(View.VISIBLE);

                }
                if (count >= i2) {
                    fa2.setVisibility(View.VISIBLE);
                }
                if (count >= i3) {
                    fa3.setVisibility(View.VISIBLE);
                }
                if (count >= i4) {
                    fa4.setVisibility(View.VISIBLE);
                }
            }
        });



        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bg0>=1) {
                    ct.cancel();

                }

                finish();
                Intent i = new Intent(Gameplay.this, Gameplay.class);
                startActivity(i);
            }
        });


    }
    public static String value()
    {
        String data=counter.getText().toString();
        return data;
    }


    @Override
    public void onBackPressed() {
        if(bg0>=1) {
            ct.cancel();

        }
        mp.stop();
        finish();
        Intent i=new Intent(Gameplay.this,Home.class);
        startActivity(i);

    }
}
