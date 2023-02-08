package com.example.xarajatlarhisobi

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.xarajatlarhisobi.Database.AppDatabase
import com.example.xarajatlarhisobi.Models.Report
import com.example.xarajatlarhisobi.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var appDatabase: AppDatabase

    lateinit var FullList: ArrayList<Report>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        appDatabase = AppDatabase.getInstance(this)

        FullList = ArrayList()

        FullList = appDatabase.reportDao().getAllReport() as ArrayList<Report>


        binding.btnLogin.setOnClickListener {


            for (report in FullList) {


                if (report.username == binding.etUsername.text.toString() && report.password == binding.etPassword.text.toString()) {

                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()

                } else {
                    Toast.makeText(this, "Ma'lumotlarni to'g'ri kiriting", Toast.LENGTH_SHORT)
                        .show()
                }

            }

        }


    }
}