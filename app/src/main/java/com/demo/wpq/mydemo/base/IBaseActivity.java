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
     * ToolBar自定义居中的Title
     * @return 当前Activity标题栏标题
     */
    String getToolBarTitle();

    /**
     * init views and listeners etc.
     *
     * @param savedInstanceState bundle for savedInstanceState
     */
    void init(@Nullable Bundle savedInstanceState);
}
