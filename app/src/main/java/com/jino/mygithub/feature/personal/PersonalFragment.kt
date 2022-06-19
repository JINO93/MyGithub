package com.jino.mygithub.feature.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jino.mygithub.base.BaseFragment
import com.jino.mygithub.databinding.FragmentPersonalBinding
import com.jino.mygithub.extend.viewbinding.bindViews

class PersonalFragment : BaseFragment() {

    private val mViewBinding by bindViews<FragmentPersonalBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mViewBinding?.root
    }


    override fun initListener() {
    }

    override fun initView() {
    }

    override fun initData() {
    }

}