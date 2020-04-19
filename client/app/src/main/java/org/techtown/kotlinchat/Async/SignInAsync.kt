package org.techtown.kotlinchat.Async

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import org.json.JSONException
import org.json.JSONObject
import org.techtown.kotlinchat.Activity.MainActivity
import org.techtown.kotlinchat.Activity.SignInActivity
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class SignInAsync : AsyncTask<String, Void, String>{

    private val serverURL = "http://119.201.70.85/kotlin_chat/signin.php"
    private var context : Context
    private var activity : SignInActivity
    private val TAG = "SignInAsync"
    private lateinit var user_ID : String

    constructor(context : Context, activity: SignInActivity) : super()
    {
        this.context = context
        this.activity = activity
    }


    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        isComplete(result)
    }

    override fun doInBackground(vararg params: String?): String {
        var user_ID = params[0]
        var user_password = params[1]

        var postParameters ="user_ID=$user_ID&user_password=$user_password"

        this.user_ID = user_ID.toString()

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

    //로그인 완료 여부 판단
    private fun isComplete(result: String?)
    {
        try {
            var jsonObject = JSONObject(result)
            var list = jsonObject.getJSONArray("result")

            for(i in 0..list.length()-1)
            {
                var c = list.getJSONObject(i)
                var title = c.getString("title")//로그인 성공, 실패 여부

                if(title.equals("fail"))//로그인 실패
                {
                    var cause = c.getString("cause")

                    //에러 메시지 띄움
                    activity.runOnUiThread(Runnable {
                        if(cause.equals("password wrong"))
                            Toast.makeText(context,"비밀번호가 틀렸습니다.",Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(context,"해당 ID가 존재하지 않습니다.",Toast.LENGTH_SHORT).show()
                    })

                }
                else//로그인 성공
                {
                    var intent = Intent(context, MainActivity::class.java)
                    intent.putExtra("userID",user_ID)
                    context.startActivity(intent)
                    activity.finish()
                }
            }
        }
        catch (e : Exception)
        {
            e.printStackTrace()
        }
    }

}