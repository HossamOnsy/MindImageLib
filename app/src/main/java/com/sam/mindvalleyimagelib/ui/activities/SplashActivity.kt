package com.sam.mindvalleyimagelib.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.sam.mindvalleyimagelib.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lottie_animation.setAnimation("lottie.json")
        lottie_animation.playAnimation()

        Handler().postDelayed({
            goToMain()

        }, 3500)
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        Animatoo.animateFade(this)
        finish()
    }
}
