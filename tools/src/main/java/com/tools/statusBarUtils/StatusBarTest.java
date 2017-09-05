package com.tools.statusBarUtils;

import android.app.Activity;
import android.graphics.Color;

/**
 * 状态栏设置方法
 */

public class StatusBarTest {


    public void Test(Activity activity) {
        //设置状态栏背景色
        StatusBarCompat.setStatusBarColor(activity, Color.BLACK, 80);


        //设置状态栏为透明
        StatusBarCompat.translucentStatusBar(activity, true);

    }

}
