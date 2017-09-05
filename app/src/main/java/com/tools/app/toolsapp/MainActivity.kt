package com.tools.app.toolsapp

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tools.statusBarUtils.StatusBarCompat
import com.tools.util.BarUtils
import com.tools.util.Utils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //设置状态栏背景色
//        StatusBarCompat.setStatusBarColor(this, Color.BLACK, 80)

//        BarUtils.setStatusBarColor(this, Color.RED, 80)
        BarUtils.setStatusBarAlpha(this)
    }
}
