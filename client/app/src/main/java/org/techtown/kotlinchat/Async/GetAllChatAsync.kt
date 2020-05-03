package org.techtown.kotlinchat.Async

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import org.json.JSONObject
import org.techtown.kotlinchat.Activity.MainActivity
import org.techtown.kotlinchat.Activity.SignInActivity
import org.techtown.kotlinchat.Fragment.FragmentAllChat
import org.techtown.kotlinchat.Item.ChatItem
import org.techtown.kotlinchat.MyApplication
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class GetAllChatAsync : AsyncTask<String, Void, String> {

    private val serverURL = "http://${MyApplication.INSTANCE.IP_address}/kotlin_chat/getAllChat.php"
    private var context : Context
    private var fragmentAllChat : FragmentAllChat
    private val TAG = "GetAllChatAsync"


    constructor(context : Context, fragmentAllChat: FragmentAllChat) : super()
    {
        this.context = context
        this.fragmentAllChat = fragmentAllChat
    }


    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        isComplete(result)
    }

    override fun doInBackground(vararg params: String?): String {

        try {
            var url = URL(serverURL)
            val httpURLConnection = url.openConnection() as HttpURLConnection

            httpURLConnection.readTimeout = 5000
            httpURLConnection.connectTimeout = 5000
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.connect()

            val outputStream = httpURLConnection.outputStream
            outputStream.flush()
            outputStream.close()


            val responseStatusCode = httpURLConnection.responseCode

            val inputStream: InputStream
            if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.inputStream
            } else {
                inputStream = httpURLConnection.errorStream
            }

            val inputStreamReader = InputStreamReader(inputStream, "UTF-8")
            val bufferedReader = BufferedReader(inputStreamReader)

            val sb = StringBuilder()
            var line: String? = bufferedReader.readLine()

            Log.d(TAG, line?.toString())
            while (line != null) {

                sb.append(line)
                line = bufferedReader.readLine()
            }

            bufferedReader.close()
            return sb.toString()
        }
        catch (e : Exception)
        {
            e.printStackTrace()
            return "Error : ${e.message}"
        }

    }

    //chatting room 전체 정보
    private fun isComplete(result: String?)
    {
        try {
            var jsonObject = JSONObject(result)
            var list = jsonObject.getJSONArray("result")

            var items : ArrayList<ChatItem> = ArrayList()

            for(i in 0..list.length()-1)
            {
                var c = list.getJSONObject(i)
                var title = c.getString("title")
                var chat_num = c.getString("chat_num")
                var chatroom_people = c.getString("chatroom_people")

                items.add(ChatItem(chat_num,title,chatroom_people))
            }

            fragmentAllChat.setRecyclerAdater(items)
        }
        catch (e : Exception)
        {
            e.printStackTrace()
        }
    }
}