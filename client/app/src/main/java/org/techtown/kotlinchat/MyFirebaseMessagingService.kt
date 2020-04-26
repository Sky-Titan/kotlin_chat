package org.techtown.kotlinchat

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "FCM"

    override fun onMessageReceived(msg: RemoteMessage) {
        super.onMessageReceived(msg)

        Log.d(TAG,msg.data.toString())

        //TODO : 다른 액티비티로 메시지 전달해서 비동기 작업
    }


}