package com.jino.mygithub.common

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jino.mygithub.MyApplication

object ImageLoader {

    @SuppressLint("CheckResult")
    fun loadImage(url: String?, imageView: ImageView?, placeDid: Int, errorDid: Int) {
        imageView?.let {
            val options = RequestOptions().apply {
                placeholder(ContextCompat.getDrawable(MyApplication.app,placeDid))
                error(ContextCompat.getDrawable(MyApplication.app,errorDid))
            }
            Glide.with(MyApplication.app)
                .load(url)
                .apply(options)
                .into(it)
        }
    }
}