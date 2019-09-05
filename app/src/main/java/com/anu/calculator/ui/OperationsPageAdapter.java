package com.anu.calculator.ui;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class OperationsPageAdapter extends FragmentPagerAdapter {

    private static final String TAG = "OperationsPageAdapter";
    private final List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private final List<String> mFragmentTitleList = new ArrayList<String>();

    public void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
        Log.d(TAG, "addFragment: "+title);
    }

    public OperationsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    public Fragment getFragmentByTitle(String title){
        for(int i=0;i<mFragmentTitleList.size();i++){
            if (mFragmentTitleList.get(i).equals(title))
                return mFragmentList.get(i);
        }
        return null;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
