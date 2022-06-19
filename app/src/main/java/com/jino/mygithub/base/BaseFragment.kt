package com.jino.mygithub.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment:Fragment() {

    private var inited = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        if(!needLazyInit()){
            initData()
            inited = true
        }
    }

    override fun onResume() {
        super.onResume()
        if(!inited && needLazyInit()){
            inited = true
            lazyInit()
        }
    }

    abstract fun initListener()

    abstract fun initView()

    abstract fun initData()

    private fun lazyInit(){
        initData()
    }

    open fun needLazyInit() = false
}