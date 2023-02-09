package com.example.xarajatlarhisobi

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movieappviewbindingandcache.Cache.MySharedPreferenceMovie
import com.example.xarajatlarhisobi.Database.AppDatabase
import com.example.xarajatlarhisobi.Database.AppDatabaseR
import com.example.xarajatlarhisobi.Models.Registr
import com.example.xarajatlarhisobi.Models.Report
import com.example.xarajatlarhisobi.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    lateinit var appDatabase: AppDatabase
    lateinit var appDatabaseR: AppDatabaseR


    lateinit var FullList: ArrayList<Report>
    lateinit var FullListR: ArrayList<Registr>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        MySharedPreferenceMovie.init(this)

        appDatabase = AppDatabase.getInstance(this)
        appDatabaseR = AppDatabaseR.getInstance(this)

        FullList = ArrayList()
        FullListR = ArrayList()

        FullList = appDatabase.reportDao().getAllReport() as ArrayList<Report>

        FullListR = appDatabaseR.registrDao().getAllRegistr() as ArrayList<Registr>


        binding.btnLogin.setOnClickListener {

            for (registr in FullListR) {


                if (registr.username == binding.etUsername.text.toString() && registr.password == binding.etPassword.text.toString()) {

                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                    Toast.makeText(this, "Accountinggizga kirdinggiz", Toast.LENGTH_SHORT).show()
                    MySharedPreferenceMovie.user = binding.etUsername.text.toString()
                    MySharedPreferenceMovie.pass = binding.etPassword.text.toString()

                } else {
                    Toast.makeText(this, "Ma'lumotlarni to'g'ri kiriting", Toast.LENGTH_SHORT)
                        .show()
                }

            }

        }


    }
}