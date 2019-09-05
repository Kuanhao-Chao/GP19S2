package com.anu.calculator.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.anu.calculator.R;
import com.anu.calculator.functionExpression.Function;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity implements HistoryMessenger {

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
        mViewPager.setOffscreenPageLimit(100);
        setupViewOperationsPager(mViewPager);

        // Setup the Tab Layout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.operations_tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // Focus on the Calculation Text Area
        final EditText calculation_area = findViewById(R.id.calculation_textarea);
        calculation_area.setShowSoftInputOnFocus(false);
    }

    /**
     * This is the interface that allows other fragments to communicate with the history fragment.
     * By design, child fragments should not directly communicate, but instead communicate with the
     * parent activity, which in this case is the MainActivity (this).
     *
     * This method finds the history fragment and calls it's addHistory method which it then deals
     * with the repercussions of.
     *
     * @author: Michael Betterton (u6797866)
     * @param data The data to pass to the history fragment
     */
    @Override
    public void sendHistory(String data) {
        // Get the history fragment by find it in the page adapter
        HistoryFragment f = (HistoryFragment) mOperationsPageAdapter.getFragmentByTitle(getString(R.string.tab_history));
        // Send it the new history message received from the child fragment.
        f.addHistory(data);
    }

    /**
     * Takes a ViewPager and adds all Fragments to the viewPager by first adding them to a
     * OperationsPageAdapter, which is a FragmentPagerAdapter. The OperationsPageAdapter extends
     * FragmentPagerAdapter and adds several useful methods for retrieval of fragments based on
     * name, tag and id. It also keeps track of the total number of fragments in operation.
     *
     * @author: Michael Betterton (u6797866)
     * @param viewPager The ViewPager to add Fragments to.
     */
    private void setupViewOperationsPager(ViewPager viewPager) {
        mOperationsPageAdapter.addFragment(new DigitFragment(), getString(R.string.tab_basic));
        mOperationsPageAdapter.addFragment(new OperationsFragment(), getString(R.string.tab_scientific));
        mOperationsPageAdapter.addFragment(new HistoryFragment(), getString(R.string.tab_history));
        mOperationsPageAdapter.addFragment(new GraphFragment(), getString(R.string.tab_graph));
        mOperationsPageAdapter.addFragment(new FunctionFragment(), getString(R.string.tab_function));
        viewPager.setAdapter(mOperationsPageAdapter);
    }

}
