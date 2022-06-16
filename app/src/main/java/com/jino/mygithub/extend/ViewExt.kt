package com.jino.mygithub.extend

import android.view.View

fun View.gone(){
    visibility = View.GONE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.visibleIf(invisibleType:Int = View.INVISIBLE, predict:()->Boolean){
    visibility = if(predict()) View.VISIBLE else invisibleType
}