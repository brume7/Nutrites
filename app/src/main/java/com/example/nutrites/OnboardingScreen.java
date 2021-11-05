package com.example.nutrites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnboardingScreen extends AppCompatActivity {

    ViewPager mobviewPager;
    LinearLayout mobdotLayer;
    Button backbob, nextbob, skipbob;

    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_screen);

        backbob = findViewById(R.id.backbob);
        nextbob= findViewById(R.id.nextbob);
        skipbob = findViewById(R.id.skipbob);

        backbob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getitem(0) > 0){
                    mobviewPager.setCurrentItem(getitem(-1),true);
                }


            }
        });

        nextbob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) < 2)
                    mobviewPager.setCurrentItem(getitem(1),true);
                else {
                    Intent i  = new Intent(OnboardingScreen.this, LoginActivity.class);
                    startActivity(i);
                    finish();

                }

            }
        });

        skipbob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i  = new Intent(OnboardingScreen.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        mobviewPager = (ViewPager) findViewById(R.id.obviewPager);
        mobdotLayer = (LinearLayout) findViewById(R.id.obindicator_layout);

        viewPagerAdapter = new ViewPagerAdapter(this);

        mobviewPager.setAdapter(viewPagerAdapter);

        setUpindicator(0);
        mobviewPager.addOnPageChangeListener(viewListener);

    }

    public void setUpindicator(int position){

        dots = new TextView[3];
        mobdotLayer.removeAllViews();

        for (int i = 0 ; i < dots.length ; i++){

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive));//(R.color.inactive,getApplicationContext().getTheme()));
            mobdotLayer.addView(dots[i]);
        }

        dots[position].setTextColor(getResources().getColor(R.color.active));

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setUpindicator(position);

            if (position > 0){

                backbob.setVisibility(View.VISIBLE);

            }else {

                backbob.setVisibility(View.INVISIBLE);

            }

            if (position == 2){
                skipbob.setVisibility(View.INVISIBLE);
            }else{
                skipbob.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int i){

        return mobviewPager.getCurrentItem() + i;
    }
}