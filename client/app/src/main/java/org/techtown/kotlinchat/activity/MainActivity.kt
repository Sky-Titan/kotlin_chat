package org.techtown.kotlinchat.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.tasks.OnCompleteListener

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import org.techtown.kotlinchat.adapter.ViewPagerAdapter
import org.techtown.kotlinchat.async.SendTokenToServerAsync
import org.techtown.kotlinchat.fragment.FragmentAllChat
import org.techtown.kotlinchat.fragment.FragmentMyChat
import org.techtown.kotlinchat.MyApplication
import org.techtown.kotlinchat.NonSwipeViewPager
import org.techtown.kotlinchat.R

class MainActivity : AppCompatActivity() {

    var prevMenuItem: MenuItem? = null
    lateinit var fragmentAllChat: FragmentAllChat
    lateinit var fragmentMyChat: FragmentMyChat

    private lateinit var mViewPager: NonSwipeViewPager

    private lateinit var user_ID:String
    private val TAG = "MainActivity"

    private val mOnNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                val fr: Fragment? = null
                val fm: FragmentManager? = null
                val fragmentTransaction: FragmentTransaction? = null
                when (item.itemId) {
                    R.id.navigation_home -> {
                        mViewPager.currentItem = 0
                        return true
                    }
                    R.id.navigation_dashboard -> {
                        mViewPager.currentItem = 1

                        return true
                    }

                }
                return false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewPager()

        user_ID = MyApplication.INSTANCE.user_ID

        SearchingToken()

        //앱을 재시작하더라도 fcm유지
        FirebaseMessaging.getInstance().isAutoInitEnabled =true
    }

    private fun setupViewPager() {

        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        mViewPager = findViewById(R.id.fragment_container) as NonSwipeViewPager
        mViewPager.setPagingDisabled() //터치 스와이프 못하게 하기

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {}
            override fun onPageSelected(i: Int) {
                if (prevMenuItem != null) {
                    prevMenuItem!!.isChecked = false
                } else {
                    navigation.menu.getItem(0).isChecked = false
                }
                navigation.menu.getItem(i).isChecked = true
                prevMenuItem = navigation.menu.getItem(i)
            }

            override fun onPageScrollStateChanged(i: Int) {}
        })

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        fragmentAllChat = FragmentAllChat()
        fragmentMyChat = FragmentMyChat()
        viewPagerAdapter.addFragment(fragmentAllChat)
        viewPagerAdapter.addFragment(fragmentMyChat)
        mViewPager.setAdapter(viewPagerAdapter)
    }

    fun SearchingToken()
    {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener {
                //토큰 가져오기 실패
                if(!it.isSuccessful){
                    Log.w(TAG,"get instaceID failed",it.exception)
                }
                else
                {
                    MyApplication.INSTANCE.token = it.getResult()?.token
                    sendToken(MyApplication.INSTANCE.token)
                    Log.d(TAG,"token : $MyApplication.INSTANCE.token")
                }
            })
    }

    fun sendToken(token:String?)
    {
        var sendTokenToServerAsync = SendTokenToServerAsync(this,this)
        sendTokenToServerAsync.execute(user_ID,token)
    }


}
