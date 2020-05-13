package org.techtown.kotlinchat.async

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import androidx.databinding.ObservableArrayList
import org.json.JSONObject
import org.techtown.kotlinchat.item.ChatItem
import org.techtown.kotlinchat.MyApplication
import org.techtown.kotlinchat.activity.MainActivity
import org.techtown.kotlinchat.activity.SignInActivity
import org.techtown.kotlinchat.adapter.RAdapter
import org.techtown.kotlinchat.fragment.AllChatViewModel
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class GetAllChatAsync(var context: Context, var activity : SignInActivity) : AsyncTask<String, Void, String> (){

    private val serverURL = "http://${MyApplication.INSTANCE.IP_address}/kotlin_chat/getAllChat.php"

    private val TAG = "GetAllChatAsync"

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        isComplete(result)
    }

    override fun doInBackground(vararg params: String?): String {

        try {
            var url = URL(serverURL)
            val httpURLConnection = url.openConnection() as HttpURLConnection


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

            var chatList:ObservableArrayList<ChatItem> = MyApplication.INSTANCE.chatList

            for(i in 0..list.length()-1)
            {
                var c = list.getJSONObject(i)
                var title = c.getString("title")
                var chat_num = c.getString("chat_num")
                var chatroom_people = c.getString("chatroom_people")

                chatList.add(ChatItem(chat_num,title,chatroom_people))

            }

            //메인 액티비티로 이동
            var intent = Intent(context, MainActivity::class.java)

            context.startActivity(intent)
            activity.finish()

        }
        catch (e : Exception)
        {
            e.printStackTrace()
        }
    }
}