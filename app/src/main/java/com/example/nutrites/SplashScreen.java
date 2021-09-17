package com.example.nutrites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    private static int Splash_Screen = 5000;

    //variables
    LottieAnimationView lottieAnimationView;
    TextView splashText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        lottieAnimationView = findViewById(R.id.lottie);//variable initialisation
        splashText = findViewById(R.id.SplashText);//variable initialisation

        splashText.animate().translationX(-1600).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationX(-1600).setDuration(1000).setStartDelay(4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,OnboardingScreen.class);
                startActivity(intent);
                finish();
            }
        },Splash_Screen);
    }
}