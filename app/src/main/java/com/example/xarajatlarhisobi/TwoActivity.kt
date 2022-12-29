package com.example.xarajatlarhisobi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.xarajatlarhisobi.Database.AppDatabase
import com.example.xarajatlarhisobi.Models.Report
import com.example.xarajatlarhisobi.databinding.ActivityTwoBinding
import com.google.android.material.snackbar.Snackbar

class TwoActivity : AppCompatActivity() {

    lateinit var binding: ActivityTwoBinding
    lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTwoBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding.bottomNavigationView.selectedItemId = R.id.plusMenu

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

                R.id.plusMenu -> {

                    true

                }


                R.id.statistikaMenu -> {

                    startActivity(Intent(applicationContext, ThreeActivity::class.java))
                    overridePendingTransition(0, 0)
                    true

                }


                R.id.infoMenu -> {


                    startActivity(Intent(applicationContext, FourActivity::class.java))
                    overridePendingTransition(0, 0)
                    true

                }

            }


            false
        }


    }

    override fun onResume() {
        super.onResume()

        var report = Report()

        appDatabase = AppDatabase.getInstance(this)

        binding.productImageId.setOnClickListener {



        }

        binding.productSave.setOnClickListener {

            if (binding.objectNameId.text.trim().isNotEmpty() && binding.productNameId.text.trim()
                    .isNotEmpty() && binding.productPriceId.text.trim()
                    .isNotEmpty()
            ) {

                report.objectName = binding.objectNameId.text.toString()
                report.productType = binding.productTypeId.selectedItemPosition
                report.produvtName = binding.productNameId.text.toString()
                report.productPrice = binding.productPriceId.text.toString()
                report.productCommet = binding.productCommentId.text.toString()


                appDatabase.citizenDao().addReport(report)
                Snackbar.make(it, "Ma'lumotlar kiritildi", 3000).show()

            } else {

                Snackbar.make(it, "Zarur ma'lumotlarni kiriting", 3000).show()

            }

        }


    }


}