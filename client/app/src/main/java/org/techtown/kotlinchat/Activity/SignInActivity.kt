package org.techtown.kotlinchat.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.techtown.kotlinchat.R
import org.techtown.kotlinchat.Async.SignInAsync

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //로그인
        signin_btn_signin.setOnClickListener(View.OnClickListener {
            var signInAsync = SignInAsync(this, this)
            signInAsync.execute(userID_edit_signin.text.toString(), userPW_edit_signin.text.toString())
        })

        signup_btn_signup.setOnClickListener(View.OnClickListener {
            var intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        })
    }
}
