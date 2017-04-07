package com.demo.wpq.mydemo.base;

import android.os.Bundle;

/**
 * Created by lion on 2017/4/7.
 */

public interface IBaseView {

    /**
     * setContentView(int layoutResID)
     *
     * @return e.g. R.layout.main
     */
    int getContentViewLayoutID();
    /**
     * init views and listeners etc.
     *
     * @param savedInstanceState
     */
    void initViewsAndEvents(Bundle savedInstanceState);
}
