package com.ibram.hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplachActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach)
        Handler().postDelayed({
            startActivity(Intent(this , AuthActivity::class.java))
            finish()
        } , 2000)
    }
}
