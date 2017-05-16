package com.demo.wpq.mydemo.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
/**
 * Explain: for {@link com.flyco.tablayout.SlidingTabLayout}
 * Author: WuPuquan
 * Time: 2017/3/10 20:53
 */
public class TabLayoutFragmentPageAdapter extends FragmentPagerAdapter {

    private String[] mTitles;
    private ArrayList<Fragment> mFragments;

    public TabLayoutFragmentPageAdapter(FragmentManager fm, String[] mTitles, ArrayList<Fragment> mFragments) {
        super(fm);
        this.mTitles = mTitles;
        this.mFragments = mFragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}