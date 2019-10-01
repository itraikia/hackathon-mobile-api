package com.ibram.hackathon

import android.os.AsyncTask
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.MalformedURLException
import java.net.ProtocolException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class someTask() : AsyncTask<Void, Void, String>() {
    override fun doInBackground(vararg params: Void?): String? {
        val url = "url"
        val email = params[1].toString()
        val password = params[2].toString()
        var data = ""
        val jsonObject = JSONObject()
        try {
            jsonObject.put("email", email)
            jsonObject.put("password", password)
        } catch (e: JSONException) {
            e.printStackTrace()
        }


        try {
            //Open Url Connection and set header
            val myUrl = URL(url)
            val httpsURLConnection: HttpsURLConnection
            httpsURLConnection = myUrl.openConnection() as HttpsURLConnection
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
            var line = ""
            do {
                stringBuffer.append(line)
                line = bufferedReader.readLine()
            } while (line != null)
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
        return "nothing"
    }

    override fun onPreExecute() {
        super.onPreExecute()
        // ...
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        // ...
    }
}