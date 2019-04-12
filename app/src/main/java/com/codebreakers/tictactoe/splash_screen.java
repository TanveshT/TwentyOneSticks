package com.codebreakers.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class splash_screen extends AppCompatActivity {

    TextView txt_21_sticks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        txt_21_sticks = findViewById(R.id.txt_splash_screen_id);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.splash_transition);

        txt_21_sticks.startAnimation(myanim);

        final Intent intent = new Intent(this,MainActivity.class);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();

    }
}
