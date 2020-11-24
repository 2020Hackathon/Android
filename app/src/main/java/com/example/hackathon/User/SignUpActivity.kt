package com.example.hackathon.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hackathon.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        Previous()
        SignNext()
    }
    fun Previous(){
        button_Previous.setOnClickListener {
            finish()
        }
    }
    fun SignNext(){
        button_sign_next.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            if (editText_sign_id.text.toString().length >= 6 && editText_sign_password.text.toString() == editText_sign_confirmpassword.text.toString() && editText_sign_password.text.toString().length >= 6){
                intent.putExtra("name", editText_sign_name.text.toString())
                intent.putExtra("id",editText_sign_id.text.toString())
                intent.putExtra("password",editText_sign_password.text.toString())
                startActivity(intent)
            }
            else if(editText_sign_id.text.toString().length <7 || editText_sign_password.text.toString().length < 7){
                Toast.makeText(this@SignUpActivity,"아이디어나 비밀번호를 6자 이상 적어주세요",Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this@SignUpActivity,"비밀번호가 같지않습니다.",Toast.LENGTH_SHORT).show()
            }
        }
    }
}