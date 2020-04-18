package org.techtown.kotlinchat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class SignInActivity : AppCompatActivity() {

    lateinit var signin_btn : Button
    lateinit var signup_btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signin_btn = findViewById(R.id.signin_btn) as (Button)
        signup_btn = findViewById(R.id.signup_btn) as (Button)

        signin_btn.setOnClickListener(View.OnClickListener {

        })
    }
}
