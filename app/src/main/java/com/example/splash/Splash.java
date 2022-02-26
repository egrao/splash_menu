package com.example.splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView logo1 = (TextView) findViewById(R.id.TextViewTopTitle);
        Animation fade1 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logo1.startAnimation(fade1);
        MediaPlayer audio = MediaPlayer.create(Splash.this, R.raw.ohbanana);
        audio.start();

        Animation spinin = AnimationUtils.loadAnimation(this, R.anim.custom_anim);
        LayoutAnimationController controller =
                new LayoutAnimationController(spinin);

        TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
        TableRow row = (TableRow) table.getChildAt(0);
        row.setLayoutAnimation(controller);


//        for (int i = 0; i < table.getChildCount(); i++) {
//            TableRow row = (TableRow) table.getChildAt(i);
//            row.setLayoutAnimation(controller);
//        }

//        Animation fade2 = AnimationUtils.loadAnimation(this, R.anim.fade_in2);
//        TextView logo2 = (TextView) findViewById(R.id.TextViewBottomTitle);
//        logo2.startAnimation(fade2);

        fade1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                startActivity(new Intent(Splash.this,Menu.class));
                startActivity(new Intent(Splash.this,login.class));
                Splash.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
// Stop the animation
        TextView logo1 = (TextView) findViewById(R.id.TextViewTopTitle);
        logo1.clearAnimation();
//        TextView logo2 = (TextView) findViewById(R.id.TextViewBottomTitle);
//        logo2.clearAnimation();
// ... stop other animations
        TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
        TableRow row = (TableRow) table.getChildAt(0);
        row.clearAnimation();
//        for (int i = 0; i < table.getChildCount(); i++) {
//            TableRow row = (TableRow) table.getChildAt(i);
//            row.clearAnimation();
//        }

    }
}