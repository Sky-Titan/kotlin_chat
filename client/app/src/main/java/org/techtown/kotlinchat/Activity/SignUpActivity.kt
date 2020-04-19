package org.techtown.kotlinchat.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.techtown.kotlinchat.Async.SignInAsync
import org.techtown.kotlinchat.Async.SignUpAsync
import org.techtown.kotlinchat.R

class SignUpActivity : AppCompatActivity() {

    private val TAG = "SignUpActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)



    }

    fun signupClicked(v : View)
    {
        if(userPW_edit_signup.text.toString().equals(userPW_Confirm_edit_signup.text.toString()))
        {
            var signUpAsync = SignUpAsync(this, this)
            signUpAsync.execute(userID_edit_signup.text.toString().trim(), userPW_edit_signup.text.toString().trim())
        }
        else//비밀번호 다름
        {
            Toast.makeText(this,"비밀번호가 다릅니다.",Toast.LENGTH_SHORT).show()
        }

        
        signup_btn_signup.setOnClickListener(View.OnClickListener {
                
        })

    }

}
