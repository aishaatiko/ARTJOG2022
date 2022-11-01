package com.alicea.artjog2022

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class SplashScreenActivity : AppCompatActivity() {
    private val loading_time = 4000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        if(supportActionBar !=null)
            this.supportActionBar?.hide();

        Handler().postDelayed(Runnable {
            val mainActivityIntent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(mainActivityIntent)
            finish()
        }, loading_time.toLong())
    }
}