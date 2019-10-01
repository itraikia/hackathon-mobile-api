package com.ibram.hackathon


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_log_in.view.*
import kotlinx.android.synthetic.main.fragment_log_in.view.signup
import kotlinx.android.synthetic.main.fragment_sign_in.view.*


class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        view.signup_complete.setOnClickListener {
            val username = view.username.text.toString().trim()
            val phone = view.phone.text.toString().trim()
            val email = view.email.text.toString().trim()
            val password = view.password.text.toString().trim()

            startActivity(Intent(activity , MainActivity::class.java))
            activity!!.finish()
        }
        return view
    }


}
