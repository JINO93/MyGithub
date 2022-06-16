package com.jino.mygithub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jino.mygithub.feature.login.LoginActivity
import com.jino.mygithub.feature.login.LoginWebActivity
import com.jino.mygithub.util.PreferenceObject

class SplashActivity : AppCompatActivity() {

    private val tokenSp by PreferenceObject(SP_GH_ACCESSTOKEN,"")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)



        if(tokenSp.isEmpty()){
            startActivity(Intent(this, LoginActivity::class.java))
        }else{
            startActivity(Intent(this,MainActivity::class.java))
        }
        finish()
    }
}