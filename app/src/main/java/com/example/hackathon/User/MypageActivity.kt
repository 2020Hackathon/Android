package com.example.hackathon.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.hackathon.API.RetrofitHelper
import com.example.hackathon.DTO.Data
import com.example.hackathon.DTO.User
import com.example.hackathon.FileInfoActivity
import com.example.hackathon.R
import kotlinx.android.synthetic.main.activity_mypage.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)
        var text_join = intent.getStringExtra("join")
        join_text.setText(text_join)
        join_text.setOnClickListener {
            val intent = Intent(this,FileInfoActivity::class.java)
            startActivity(intent)
        }
    }
    fun Mypage() {
        val id = intent.getStringExtra("id")
        if (id != null) {
            Log.d("error",id)
            RetrofitHelper().getUserAPI().getUser(id).enqueue(object : Callback<Data> {
                override fun onResponse(call: Call<Data>, response: Response<Data>) {
                    if (response.isSuccessful) {
                        Log.d("error",id)
                        if (response.body() != null) {
                            Log.d("error",response.body()!!.user.name )
                            my_name!!.text = response.body()!!.user.name
                            my_school!!.text = response.body()!!.user.userInfo
                            if (my_school.text != null){
                                my_now.text.toString() == "재학중"
                            }
                            else {
                                my_now.text.toString() == "졸업/일반인"
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<Data>, t: Throwable) {
                    Toast.makeText(this@MypageActivity,"어라 이게 왜 이러지",Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}