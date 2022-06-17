package com.jino.mygithub.extend.viewbinding

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

inline fun <reified VB : ViewBinding> Activity.bindViews() = lazy {
    (VB::class.java.inflateMethod()
        .invoke(VB::class.java,layoutInflater) as VB).apply{
         setContentView(root)
    }
}

inline fun <reified VB: ViewBinding> Fragment.bindViews() = lazy {
    var vb:VB? = null
    lifecycle.addObserver(object :DefaultLifecycleObserver{
        override fun onCreate(owner: LifecycleOwner) {
            super.onCreate(owner)
            vb = (VB::class.java.inflateMethod()
                .invoke(VB::class.java,layoutInflater) as VB)
            this@bindViews.viewLifecycleOwnerLiveData.observe(this@bindViews){
                it.lifecycle.addObserver(object :DefaultLifecycleObserver{
                    override fun onDestroy(owner: LifecycleOwner) {
                        super.onDestroy(owner)
                        vb = null
                    }
                })
            }
        }
    })
    vb
}



fun <T> Class<T>.inflateMethod() = getMethod("inflate", LayoutInflater::class.java)

fun <T> Class<T>.bindMethod() = getMethod("bind", View::class.java)