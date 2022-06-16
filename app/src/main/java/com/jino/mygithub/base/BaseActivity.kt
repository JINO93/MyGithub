package com.jino.mygithub.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolBar()

        initView()

        initListener()
    }

    abstract fun initListener()

    abstract fun initView()

    abstract fun setupToolBar()

}