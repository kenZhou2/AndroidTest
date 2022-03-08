package com.datechnologies.androidtest.animation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.datechnologies.androidtest.MainActivity;
import com.datechnologies.androidtest.R;
import com.datechnologies.androidtest.chat.ChatActivity;

/**
 * Screen that displays the D & A Technologies logo.
 * The icon can be moved around on the screen as well as animated.
 * */

public class AnimationActivity extends AppCompatActivity {

    //==============================================================================================
    // Class Properties
    //==============================================================================================

    //==============================================================================================
    // Static Class Methods
    //==============================================================================================

    public static void start(Context context)
    {
        Intent starter = new Intent(context, AnimationActivity.class);
        context.startActivity(starter);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================

    ImageView imageView;
    float x = 0,y =0;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Animation");


        imageView = (ImageView)findViewById(R.id.datech_logo);

        //Touch and drag the D & A Technologies logo around the screen.
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        x = event.getX();
                        y = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float dx,dy;
                        dx = event.getX();
                        dy = event.getY();

                        float distanceX = dx - x;
                        float distanceY = dy - y;

                        imageView.setX(imageView.getX() + distanceX);
                        imageView.setY(imageView.getY() + distanceY);

                        break;

                }
                return true;
                //return false;
            }
        });

        // TODO: Add a bonus to make yourself stick out. Music, color, fireworks, explosions!!!
    }

    // When the fade button is clicked, you must animate the D & A Technologies logo.
    // It should fade from 100% alpha to 0% alpha, and then from 0% alpha to 100% alpha
    public void animationIntent(View v) {
        imageView.animate().alpha(0).setDuration(1000).setInterpolator(new DecelerateInterpolator()).withEndAction(new Runnable() {
            @Override
            public void run() {
                imageView.animate().alpha(1).setDuration(1000).setInterpolator(new AccelerateInterpolator()).start();
            }
        }).start();
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
