package com.example.xarajatlarhisobi

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movieappviewbindingandcache.Cache.MySharedPreferenceRegistration
import com.example.xarajatlarhisobi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater,null,false)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        MySharedPreferenceRegistration.init(this)

/*        Toast.makeText(this, "${MySharedPreferenceRegistration.NumberT}", Toast.LENGTH_SHORT).show()*/

        Handler().postDelayed({

            if (MySharedPreferenceRegistration.NumberT == "{}" || MySharedPreferenceRegistration.NumberT == "0") {
                startActivity(Intent(this, RegistrationActivity::class.java))
                finish()
            } else {
                if (MySharedPreferenceRegistration.NumberT == "1") {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
            }

        }, 2500)

       // binding.textM.animation = AnimationUtils.loadAnimation(this,R.anim.anim_one)

    }
}