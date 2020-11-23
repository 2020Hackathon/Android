package com.example.hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.UiThread
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashAnimation()
    }
    @UiThread
    private fun splashAnimation(){
        val textAnim : Animation = AnimationUtils.loadAnimation(this,R.anim.anim_splash_textview)
        splashTextView.startAnimation(textAnim)
        val imageAnim : Animation = AnimationUtils.loadAnimation(this,R.anim.anim_splash_imageview)
        splashImageView.startAnimation(imageAnim)

        imageAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
            }

            override fun onAnimationEnd(animation: Animation) {
                val intent = Intent(this@SplashActivity,LoginActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.anim_splash_out_top, R.anim.anim_splash_in_down)
                finish()
            }

            override fun onAnimationRepeat(animation: Animation) {
            }

        })
    }
}