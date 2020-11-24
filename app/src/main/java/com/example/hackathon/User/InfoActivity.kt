package com.example.hackathon.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.hackathon.API.RetrofitHelper
import com.example.hackathon.DTO.SignUp
import com.example.hackathon.MainActivity
import com.example.hackathon.R
import kotlinx.android.synthetic.main.activity_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoActivity : AppCompatActivity() {

    var select = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        Login()
        Spinner()
    }
    fun Spinner(){
        val items = resources.getStringArray(R.array.my_array)

        var spinner_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        spinner.adapter = spinner_adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, l: Long) {
                select = items[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

    }
    fun Login(){
        button_info_sign_up.setOnClickListener {

            val name = intent.getStringExtra("name")
            val id  = intent.getStringExtra("id")
            val password = intent.getStringExtra("password")

            RetrofitHelper().getUserAPI().signUp(name.toString(),id.toString(),password.toString(),select).enqueue(object : Callback<SignUp>{
                override fun onResponse(call: Call<SignUp>, response: Response<SignUp>) {
                    when (response.code()){
                        200 -> {

                            Toast.makeText(this@InfoActivity , "회원가입 성공", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@InfoActivity,MainActivity::class.java)
                            intent.putExtra("name",name)
                            startActivity(intent)
                        }
                        400 -> {
                            Toast.makeText(this@InfoActivity , "이미 있는 아이디 입니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<SignUp>, t: Throwable) {
                    Toast.makeText(this@InfoActivity,"서버오류",Toast.LENGTH_SHORT).show()
                }

            })
        }
        button_info_login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}