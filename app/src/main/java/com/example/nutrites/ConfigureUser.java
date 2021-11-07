package com.example.nutrites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ConfigureUser extends AppCompatActivity {

    ViewPager logviewPage;
    ImageView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_user);

        next = findViewById(R.id.imageViewConU);

        logviewPage = findViewById(R.id.ConUviewPager);
        final ConfigureUserAdapter adapter = new ConfigureUserAdapter(getSupportFragmentManager(), this, 2);
        logviewPage.setAdapter(adapter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logviewPage.setCurrentItem(1);
            }
        });

    }
}