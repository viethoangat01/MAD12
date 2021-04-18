package com.example.mad12.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.mad12.view.fragment.DocumentFragment;
import com.example.mad12.view.fragment.HomeFragment;
import com.example.mad12.view.fragment.NotificationFragment;
import com.example.mad12.view.fragment.SelfLearningFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new SelfLearningFragment();
            case 2:
                return new NotificationFragment();
            case 3:
                return new DocumentFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
