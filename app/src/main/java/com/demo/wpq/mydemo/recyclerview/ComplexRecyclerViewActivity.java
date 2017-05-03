package com.demo.wpq.mydemo.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author wpq
 * @version 1.0
 */
public class ComplexRecyclerViewActivity extends BaseAppCompatActivity {


    @BindView(R.id.tabLayout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private String[] mTitles = {"Fragment0", "Fragment1", "Fragment2"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    public void getBundleExtras(Bundle bundle) {

    }

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_complex_recyclerview;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        for (int i = 0; i < 3; i++) {
            mFragments.add(new ComplexRecyclerViewFragment());
        }
        mViewPager.setAdapter(new TabLayoutFragmentPageAdapter(getSupportFragmentManager(), mTitles, mFragments));
        mTabLayout.setViewPager(mViewPager, mTitles, this, mFragments);
        mViewPager.setCurrentItem(0);
    }

}
