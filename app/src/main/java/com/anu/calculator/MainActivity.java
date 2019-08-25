package com.anu.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private OperationsPageAdapter mOperationsPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting");

        mOperationsPageAdapter = new OperationsPageAdapter(getSupportFragmentManager());

        // Instantiate a view pager
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewOperationsPager(mViewPager);

        // Setup the Tab Layout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.operations_tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewOperationsPager(ViewPager viewPager){
        OperationsPageAdapter operationsPageAdapter = new OperationsPageAdapter(getSupportFragmentManager());
        operationsPageAdapter.addFragment(new DigitFragment(),"Basic");
        operationsPageAdapter.addFragment(new OperationsFragment(),"Advanced");
        viewPager.setAdapter(operationsPageAdapter);
    }
}
