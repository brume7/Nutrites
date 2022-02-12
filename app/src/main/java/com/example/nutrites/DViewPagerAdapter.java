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

public class DViewPagerAdapter extends PagerAdapter {
    Context context;

    int images[] = {

            R.drawable.friedrice,
            R.drawable.lasagne,
            R.drawable.curriedsausages,
            R.drawable.chickenandvegetablestirfry,
            R.drawable.spaghetticarbonara,
            R.drawable.potatoandleeksoup,
            R.drawable.beefnachos,
            R.drawable.sausagerolls,
            R.drawable.cornfritters,
            R.drawable.paella
    };

    int title[] = {

            R.string.dinnerMeal_one,
            R.string.dinnerMeal_two,
            R.string.dinnerMeal_three,
            R.string.dinnerMeal_four,
            R.string.dinnerMeal_five,
            R.string.dinnerMeal_six,
            R.string.dinnerMeal_seven,
            R.string.dinnerMeal_eight,
            R.string.dinnerMeal_nine,
            R.string.dinnerMeal_ten
    };
    public DViewPagerAdapter(Context context) {

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
        View view = layoutInflater.inflate(R.layout.dinner_layout, container, false);

        ImageView breakfastimage = (ImageView) view.findViewById(R.id.imageViewFd);
        TextView slideHeading = (TextView) view.findViewById(R.id.dinnerViewname);
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
