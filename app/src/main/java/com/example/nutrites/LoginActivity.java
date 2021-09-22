package com.example.nutrites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {

    TabLayout logtabLayout;
    ViewPager logviewPager;
    FloatingActionButton fb,google,twitter;
    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logtabLayout = findViewById(R.id.logtabLayout);
        logviewPager = findViewById(R.id.logviewPager);
        fb = findViewById(R.id.fab_facebook);
        google = findViewById(R.id.fab_google);
        twitter = findViewById(R.id.fab_twitter);

        logtabLayout.addTab(logtabLayout.newTab().setText("Login"));
        logtabLayout.addTab(logtabLayout.newTab().setText("Signup"));
        logtabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), this,logtabLayout.getTabCount());
        logviewPager.setAdapter(adapter);

        logviewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(logtabLayout));
        logtabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                logviewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        fb.setTranslationY(300);
        google.setTranslationY(300);
        twitter.setTranslationY(300);
        logtabLayout.setTranslationY(300);

        fb.setAlpha(v);
        google.setAlpha(v);
        twitter.setAlpha(v);
        logtabLayout.setAlpha(v);

        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        twitter.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        logtabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();
    }
}