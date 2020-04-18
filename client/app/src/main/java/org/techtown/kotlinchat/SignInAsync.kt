package org.techtown.kotlinchat

import android.os.AsyncTask
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class SignInAsync : AsyncTask<String, Void, String>{

    private val serverURL = "http://119.201.70.85/kotlin_chat/signin.php"
    constructor() : super()


    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }

    override fun doInBackground(vararg params: String?): String {
        var user_ID = params[0]
        var user_password = params[1]

        var postParameters ="user_ID=$user_ID&user_password=$user_password"

        try {
            var url = URL(serverURL)
            val httpURLConnection = url.openConnection() as HttpURLConnection


            httpURLConnection.readTimeout = 5000
            httpURLConnection.connectTimeout = 5000
            httpURLConnection.requestMethod = "POST"
            httpURLConnection.connect()

            val outputStream = httpURLConnection.outputStream
            outputStream.write(postParameters.toByteArray(charset("UTF-8")))
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

            while (line != null) {
                sb.append(line)
            }

            println("json 리턴: $sb")
            bufferedReader.close()
            return sb.toString()
        }
        catch (e : Exception)
        {
            return "Error : ${e.message}"
        }

    }


}