package com.ibram.hackathon

import android.os.AsyncTask
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.ProtocolException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class LoginRequest(fragment: Fragment) : AsyncTask<String, Void, String>() {

    val frg = fragment

    @RequiresApi(Build.VERSION_CODES.N)
    override fun doInBackground(vararg params: String?): String? {
        val url = frg.activity!!.getString(R.string.ApiBaseKey)+frg.activity!!.getString(R.string.login_url)
        val email = params[0].toString()
        val password = params[1].toString()
        var data = ""
        val jsonObject = JSONObject()
        try {
            jsonObject.put("username", email)
            jsonObject.put("password", password)
        } catch (e: JSONException) {
            e.printStackTrace()
        }


        try {
            //Open Url Connection and set header
            val myUrl = URL(url)
            val httpsURLConnection: HttpURLConnection
            httpsURLConnection = myUrl.openConnection() as HttpURLConnection
            httpsURLConnection.doInput = true
            httpsURLConnection.doOutput = true
            httpsURLConnection.requestMethod = "POST"
            httpsURLConnection.setRequestProperty("Content-Type", "application/json")
            httpsURLConnection.connect()

            //write data (send body)
            val op = OutputStreamWriter(httpsURLConnection.outputStream)
            op.write(jsonObject.toString())
            op.close()

            //read response
            val inputStream = httpsURLConnection.inputStream
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val stringBuffer = StringBuffer()
            for (line in bufferedReader.lines()) {
                stringBuffer.append(line)
            }
            data = stringBuffer.toString()
            bufferedReader.close()


        } catch (e: ProtocolException) {
            e.printStackTrace()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return data
    }

    override fun onPreExecute() {
    }

    override fun onPostExecute(result: String?) {
        Log.e("Here" , result)
        val js = JSONObject(result)
        if (frg is LogInFragment)(frg as LogInFragment).auth(js.getString("token"))
        if (frg is SignInFragment)(frg as SignInFragment).auth(js.getString("token"))
    }
}