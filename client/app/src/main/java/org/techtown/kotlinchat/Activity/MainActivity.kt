package org.techtown.kotlinchat.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import org.techtown.kotlinchat.Adapter.RAdapter
import org.techtown.kotlinchat.Async.SendTokenToServerAsync
import org.techtown.kotlinchat.Item.RecyclerItem
import org.techtown.kotlinchat.MyApplication
import org.techtown.kotlinchat.R

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RAdapter
    private lateinit var myDataSet: Array<RecyclerItem>
    private lateinit var user_ID:String
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //var recyclerItem:RecyclerItem = RecyclerItem();

        user_ID = MyApplication.INSTANCE.user_ID

        recyclerView = findViewById(R.id.recyclerView)

        //adapter = RAdapter(myDataSet)
        SearchingToken()


        //앱을 재시작하더라도 fcm유지
        FirebaseMessaging.getInstance().isAutoInitEnabled =true
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

    fun initDataset()
    {
        //TODO : 서버와 통신 후 리스트 불러옴
    }

}
