package com.example.nutrites;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ConfigureUserAdapter extends FragmentPagerAdapter {

    private Context context;
    int totalTabs;

    public ConfigureUserAdapter(FragmentManager fm, Context context, int totalTabs){
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }
    @Override
    public int getCount() {
        return totalTabs;
    }

    public Fragment getItem(int position){
        switch (position) {
            case 0:
                WelcomeUserConTabFragment welcomeUserConTabFragment = new WelcomeUserConTabFragment();
                return welcomeUserConTabFragment;
            case 1:
                UserConFeedingTypeTabFragment userConFeedingTypeTabFragment = new UserConFeedingTypeTabFragment();
                SignupTapFragment signupTapFragment = new SignupTapFragment();
                return userConFeedingTypeTabFragment;
            default:
                return null;
        }
    }

}
