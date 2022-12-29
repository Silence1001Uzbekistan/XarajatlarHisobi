package com.example.xarajatlarhisobi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.xarajatlarhisobi.databinding.ActivityFourBinding
import com.example.xarajatlarhisobi.databinding.ActivityThreeBinding

class FourActivity : AppCompatActivity() {

    lateinit var binding: ActivityFourBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFourBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding.bottomNavigationView.selectedItemId = R.id.infoMenu

        binding.bottomNavigationView.setOnItemSelectedListener {

            val id = it.itemId

            when (id) {


                R.id.homeMenu -> {

                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                    overridePendingTransition(0, 0)
                    true


                }

                R.id.calculateMenu -> {

                    startActivity(Intent(applicationContext, OneActivity::class.java))
                    overridePendingTransition(0, 0)
                    true

                }

                R.id.plusMenu-> {

                    startActivity(Intent(applicationContext, TwoActivity::class.java))
                    overridePendingTransition(0, 0)
                    true

                }


                R.id.statistikaMenu -> {

                    startActivity(Intent(applicationContext, ThreeActivity::class.java))
                    overridePendingTransition(0, 0)
                    true

                }


                R.id.infoMenu -> {

                    true

                }

            }


            false
        }

    }




}