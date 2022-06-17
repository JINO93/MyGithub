package com.jino.mygithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jino.mygithub.base.BaseActivity
import com.jino.mygithub.databinding.ActivityLoginPageBinding
import com.jino.mygithub.databinding.ActivityMainBinding
import com.jino.mygithub.extend.viewbinding.bindViews
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val mViewBinding by bindViews<ActivityMainBinding>()

    override fun initListener() {
    }

    override fun initView() {
        setContentView(R.layout.activity_main)
        //绑定BottomNavigationView
        val navHost: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHost.navController
        NavigationUI.setupWithNavController(mViewBinding.tabNavi,navController)
    }

    override fun setupToolBar() {
    }
}