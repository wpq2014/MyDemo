package com.demo.wpq.mydemo.base;

import android.os.Bundle;

/**
 * Created by lion on 2017/4/7.
 */

public interface IBaseActivity extends IBaseView {

    /**
     * get data from intent
     *
     * @param bundle getIntent().getExtras();
     */
    void getBundleExtras(Bundle bundle);
}
