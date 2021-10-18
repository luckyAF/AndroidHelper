package com.luckyaf.androidhelper;

import com.luckyaf.smartandroid.base.BaseApplication;
import com.luckyaf.smartandroid.utils.ScreenAdapter;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/10/16
 */
class App extends BaseApplication {
    @Override
    public void initializeConfig() {
        super.initializeConfig();
        ScreenAdapter.setup(this);
        ScreenAdapter.register(this,480f, ScreenAdapter.MATCH_SHORT_SIDE, ScreenAdapter.MATCH_UNIT_DP);

    }
}
