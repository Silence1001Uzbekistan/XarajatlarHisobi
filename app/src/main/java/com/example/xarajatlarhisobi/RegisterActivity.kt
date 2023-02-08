package com.example.xarajatlarhisobi

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.xarajatlarhisobi.Database.AppDatabase
import com.example.xarajatlarhisobi.Models.Report
import com.example.xarajatlarhisobi.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    lateinit var appDatabase: AppDatabase
    lateinit var report: Report

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        appDatabase = AppDatabase.getInstance(this)

        supportActionBar!!.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        report = Report()


        binding.btnRegister.setOnClickListener {

            report.username = binding.etUsername.text.toString()
            report.password = binding.etPassword.text.toString()

            appDatabase.reportDao().addReport(report)

            startActivity(Intent(this,HomeActivity::class.java))

        }


    }
}