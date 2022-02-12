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

import com.airbnb.lottie.LottieAnimationView;

import java.util.Random;

public class BViewPagerAdapter extends PagerAdapter {
    Context context;

    int images[] = {

            R.drawable.chicken_waffles,
            R.drawable.avocado_toast,
            R.drawable.home_fries,
            R.drawable.pancakes,
            R.drawable.bacon_and_eggs,
            R.drawable.french_toast,
            R.drawable.sausages_and_eggs,
            R.drawable.breakfastburritosrecipe,
            R.drawable.cereal,
            R.drawable.breakfast_sandwich
    };

    int title[] = {

            R.string.breakfastMeal_one,
            R.string.breakfastMeal_two,
            R.string.breakfastMeal_three,
            R.string.breakfastMeal_four,
            R.string.breakfastMeal_five,
            R.string.breakfastMeal_six,
            R.string.breakfastMeal_seven,
            R.string.breakfastMeal_eight,
            R.string.breakfastMeal_nine,
            R.string.breakfastMeal_ten
    };

    public BViewPagerAdapter(Context context) {

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
        View view = layoutInflater.inflate(R.layout.breakfast_layout, container, false);

        ImageView breakfastimage = (ImageView) view.findViewById(R.id.imageViewFb);
        TextView slideHeading = (TextView) view.findViewById(R.id.breakfasttextViewname);
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
