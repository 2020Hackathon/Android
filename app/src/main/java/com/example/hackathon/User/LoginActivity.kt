package com.example.hackathon.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.hackathon.API.RetrofitHelper
import com.example.hackathon.DTO.UserLogin
import com.example.hackathon.MainActivity
import com.example.hackathon.R
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login()

        intent()
    }
    fun intent(){
        button_signup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
    fun login(){
        button_login.setOnClickListener {
            RetrofitHelper().getUserAPI().signIn(editText_id.text.toString(),editText_password.text.toString()).enqueue(object : Callback<UserLogin>{
                override fun onResponse(call: Call<UserLogin>, response: Response<UserLogin>) {
                    when (response.code()){
                        200 -> {
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.putExtra("id",editText_id.text.toString())
                            startActivity(intent)
                        }
                        401 -> Toast.makeText(this@LoginActivity, "로그인 실패 : 아이디나 비번이 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UserLogin>, t: Throwable) {
                    Log.d("error",t.toString())
                }

            })
        }
    }
}