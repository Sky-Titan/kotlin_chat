package org.techtown.kotlinchat.Async

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import org.json.JSONObject
import org.techtown.kotlinchat.Activity.MainActivity
import org.techtown.kotlinchat.Activity.SignInActivity
import org.techtown.kotlinchat.MyApplication
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class SendTokenToServerAsync : AsyncTask<String, Void, String> {

    private val serverURL = "http://${MyApplication.INSTANCE.IP_address}/kotlin_chat/getToken.php"
    private var context : Context
    private var activity : MainActivity
    private val TAG = "SendTokenToServerAsync"
    private lateinit var user_ID : String
    private lateinit var token : String

    constructor(context : Context, activity: MainActivity) : super()
    {
        this.context = context
        this.activity = activity
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

    }

    override fun doInBackground(vararg params: String?): String {
        var user_ID = params[0]
        var token = params[1]

        var postParameters ="user_ID=$user_ID&token=$token"

        this.user_ID = user_ID.toString()
        Log.d(TAG,"send token to server")
        try {
            var url = URL(serverURL)
            val httpURLConnection = url.openConnection() as HttpURLConnection

            httpURLConnection.readTimeout = 5000
            httpURLConnection.connectTimeout = 5000
            httpURLConnection.requestMethod = "POST"
            httpURLConnection.connect()

            val outputStream = httpURLConnection.outputStream
            outputStream.write(postParameters.toByteArray(charset("euckr")))
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

}