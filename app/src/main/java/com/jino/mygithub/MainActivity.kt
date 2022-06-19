package com.jino.mygithub

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.jino.mygithub.base.BaseActivity
import com.jino.mygithub.databinding.ActivityMainBinding
import com.jino.mygithub.extend.viewbinding.bindViews
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val mViewBinding by bindViews<ActivityMainBinding>()

    override fun initListener() {
    }

    override fun initView() {
        //绑定BottomNavigationView
        mViewBinding.navHostFragment
        val navHost: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHost.navController
//        NavigationUI.setupWithNavController(mViewBinding.tabNavi,navController)
        mViewBinding.tabNavi.setupWithNavController(navController)
    }

    override fun setupToolBar() {
    }
}