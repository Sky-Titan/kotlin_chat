package org.techtown.kotlinchat.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.techtown.kotlinchat.R

class SignUpActivity : AppCompatActivity() {

    private val TAG = "SignUpActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        
        signup_btn_signup.setOnClickListener(View.OnClickListener {
                
        })
    }
}
