package com.tools.app.toolsapp;

import android.app.Application;

import com.tools.util.Utils;

/**
 * Created by xuge on 2017/9/5.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
