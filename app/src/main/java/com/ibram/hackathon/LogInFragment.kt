package com.ibram.hackathon


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_log_in.*
import kotlinx.android.synthetic.main.fragment_log_in.view.*


class LogInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_log_in, container, false)
        view.signup.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.fragment,SignInFragment(),"Signup").commit()
        }

        view.login.setOnClickListener {
            val username = view.user.text.toString().trim()
            val password = view.pass.text.toString().trim()

            val loginRequest = LoginRequest(this)
            loginRequest.execute(username,password)
        }
        return view
    }


    fun auth(token : String ){
        val intent = Intent(activity , MainActivity::class.java)
        intent.putExtra("token" , token)
        startActivity(intent)
        activity!!.finish()
    }


}
