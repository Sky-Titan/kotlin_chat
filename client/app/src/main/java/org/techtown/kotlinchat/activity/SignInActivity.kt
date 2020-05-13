package org.techtown.kotlinchat.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.techtown.kotlinchat.R
import org.techtown.kotlinchat.async.SignInAsync

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


    }

    fun signinClicked(v: View)
    {
        var signInAsync = SignInAsync(this, this)
        signInAsync.execute(userID_edit_signin.text.toString().trim(), userPW_edit_signin.text.toString().trim())
    }
    fun signupClicked(v: View)
    {
        var intent = Intent(this,SignUpActivity::class.java)
        startActivity(intent)
    }
}
