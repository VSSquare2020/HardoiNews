package com.vssquare.hardoinews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class SplashScreen extends AppCompatActivity {
    ImageView splash_logo;
    RelativeLayout splash_layout;
    ProgressBar splash_progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        splash_logo = findViewById(R.id.splash_logo);
        splash_progress = findViewById(R.id.splash_progress);
        splash_layout = findViewById(R.id.splash_layout);
        splash_logo.startAnimation(AnimationUtils.loadAnimation(SplashScreen.this,R.anim.slide_down));
        if (CheckInternetConnection.IsNetworkAvailable(this)){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(i);
                    finish();
                    splash_progress.setVisibility(View.GONE);

                }
            },5000);
        }
        else {
            splash_progress.setVisibility(View.GONE);
            Snackbar snackbar = Snackbar.make(splash_layout,"Ops!! Please check Internet.",Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("Retry", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recreate();
                }
            }).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
            finish();
    }
}
