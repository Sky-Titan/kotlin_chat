package org.techtown.kotlinchat

import android.app.Application

class MyApplication : Application (){

    lateinit var user_ID : String
    var IP_address ="121.182.90.245"
    var token : String? = null

    override fun onCreate() {
        super.onCreate()
    }
    init {
        INSTANCE = this
    }
    companion object{
        lateinit var INSTANCE : MyApplication
    }
}