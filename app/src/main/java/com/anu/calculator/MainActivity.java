package com.anu.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    OperationsPageAdapter mOperationsPageAdapter;
    ViewPager mViewPager;

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

        // Focus on the Calculation Text Area
        final EditText calculation_area = findViewById(R.id.calculation_textarea);
        calculation_area.setShowSoftInputOnFocus(false);
    }

    private void setupViewOperationsPager(ViewPager viewPager) {
        OperationsPageAdapter operationsPageAdapter = new OperationsPageAdapter(getSupportFragmentManager());
        operationsPageAdapter.addFragment(new DigitFragment(), getString(R.string.tab_basic));
        operationsPageAdapter.addFragment(new OperationsFragment(), getString(R.string.tab_scientific));
        operationsPageAdapter.addFragment(new HistoryFragment(), getString(R.string.tab_history));
        viewPager.setAdapter(operationsPageAdapter);
    }

}
