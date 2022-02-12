package com.example.nutrites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class MealPlansTabFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.mealplans_tab_fragment, container, false);
        ViewPager breakfastviewPager;
        ViewPager lunchviewPager;
        ViewPager dinnerviewPager;
//
        BViewPagerAdapter bviewPagerAdapter;
        breakfastviewPager = (ViewPager) root.findViewById(R.id.breakfastviewPager);
        bviewPagerAdapter = new BViewPagerAdapter(getContext().getApplicationContext());
        breakfastviewPager.setAdapter(bviewPagerAdapter);

        LViewPagerAdapter lviewPagerAdapter;
        lunchviewPager = (ViewPager) root.findViewById(R.id.lunchviewPager);
        lviewPagerAdapter = new LViewPagerAdapter(getContext().getApplicationContext());
        lunchviewPager.setAdapter(lviewPagerAdapter);

        DViewPagerAdapter dviewPagerAdapter;
        dinnerviewPager = (ViewPager) root.findViewById(R.id.dinnerviewPager);
        dviewPagerAdapter = new DViewPagerAdapter(getContext().getApplicationContext());
        dinnerviewPager.setAdapter(dviewPagerAdapter);

        return root;
    }
}
