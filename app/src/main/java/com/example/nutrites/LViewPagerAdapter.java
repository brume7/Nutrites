package com.example.nutrites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.Random;

public class LViewPagerAdapter extends PagerAdapter {
    Context context;

    int images[] = {

            R.drawable.pasta_dish,
            R.drawable.fish_and_chips,
            R.drawable.curry_and_rice,
            R.drawable.tomatosoupandroll,
            R.drawable.chickenandmushroomnoodles,
            R.drawable.carrotandcoriandersoupandroll,
            R.drawable.tunanicoisesalad,
            R.drawable.pasty,
            R.drawable.jacketpotatowithfilling,
            R.drawable.prawn_sandwich
    };

    int title[] = {

            R.string.lunchMeal_one,
            R.string.lunchMeal_two,
            R.string.lunchMeal_three,
            R.string.lunchMeal_four,
            R.string.lunchMeal_five,
            R.string.lunchMeal_six,
            R.string.lunchMeal_seven,
            R.string.lunchMeal_eight,
            R.string.lunchMeal_nine,
            R.string.lunchMeal_ten
    };
    public LViewPagerAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    final int random = new Random().nextInt((9 - 0) + 1) + 0;

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.lunch_layout, container, false);

        ImageView breakfastimage = (ImageView) view.findViewById(R.id.imageViewFl);
        TextView slideHeading = (TextView) view.findViewById(R.id.lunchViewname);
//        TextView slideDesciption = (TextView) view.findViewById(R.id.textDesc);

        breakfastimage.setImageResource(images[random]);
        slideHeading.setText(title[random]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((LinearLayout) object);

    }
}
