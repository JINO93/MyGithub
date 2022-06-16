package com.jino.mygithub.feature.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.jino.mygithub.MainActivity
import com.jino.mygithub.R
import com.jino.mygithub.databinding.ActivityLoginPageBinding
import com.jino.mygithub.extend.viewbinding.bindViews

class LoginActivity : AppCompatActivity() {

    companion object{
        const val OP_LOGIN: Int = 100
        const val RESULT_SUCCESS: Int = 200
        const val RESULT_FAILED: Int = 300

    }

    private val mViewBinding by bindViews<ActivityLoginPageBinding>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        mViewBinding.skipTextview.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        mViewBinding.loginBtn.setOnClickListener {
            startActivityForResult(Intent(this, LoginWebActivity::class.java),OP_LOGIN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == OP_LOGIN){
            if(resultCode == RESULT_SUCCESS){
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }else{
                Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show()
            }
        }
    }
}