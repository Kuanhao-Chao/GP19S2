package com.anu.calculator.ui;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Small extension to the FragmentPageAdapter class that adds some helper functions for finding
 * and adding fragments to the adapter. This helps when activities are manipulating fragments once
 * they have been created.
 *
 * @author: Michael Betterton (u6797866)
 */
public class OperationsPageAdapter extends FragmentPagerAdapter {

    private static final String TAG = "OperationsPageAdapter";
    private final List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private final List<String> mFragmentTitleList = new ArrayList<String>();

    /**
     * Adds a fragment to the fragment helper classes for future use.
     *
     * @author: Michael Betterton (u6797866)
     * @param fragment The Fragment to add and store in the fragment manager.
     * @param title The title for the newly added fragment. This should be unique.
     */
    void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
        Log.d(TAG, "addFragment: "+title);
    }

    /**
     * Default constructor for the OperationsPageAdapter that just calls the fragment manager
     * constructor.
     *
     * @author: Michael Betterton (u6797866)
     * @param fm The fragment manager for the tablist.
     */
    OperationsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Given a position (index) of a fragment in the adapter, return the title of the fragment.
     *
     * @author: Michael Betterton (u6797866)
     * @param position The index of the fragment in the adapter.
     * @return The title of the fragment as a string.
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    /**
     * Given a string that represents the title of a fragment, return the fragment that this
     * adapter is managing.
     *
     * @author: Michael Betterton (u6797866)
     * @param title The title of the fragment to search for.
     * @return A fragment for the provided title or null if it could not be found.
     */
    Fragment getFragmentByTitle(String title){
        for(int i=0;i<mFragmentTitleList.size();i++){
            if (mFragmentTitleList.get(i).equals(title))
                return mFragmentList.get(i);
        }
        return null;
    }

    /**
     * Finds a fragment at the given index that the adapter is managing.
     *
     * @author: Michael Betterton (u6797866)
     * @param position The index of the fragment to get.
     * @return The fragment at the provided index.
     */
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    /**
     * Returns the count of the number of fragments that this adapter is managing.
     *
     * @author: Michael Betterton (u6797866)
     * @return The count of the total number of fragments this adapter is managing.
     */
    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
