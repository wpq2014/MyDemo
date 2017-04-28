package com.demo.wpq.mydemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by lion on 2017/4/7.
 */

public interface IBaseActivity {

    /**
     * get data from intent
     *
     * @param bundle getIntent().getExtras();
     */
    void getBundleExtras(Bundle bundle);

    /**
     * setContentView(int layoutResID)
     *
     * @return e.g. R.layout.main
     */
    int getContentViewLayoutID();

    /**
     * init views and listeners etc.
     *
     * @param savedInstanceState bundle for savedInstanceState
     */
    void init(@Nullable Bundle savedInstanceState);
}
