package com.example.xarajatlarhisobi

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movieappviewbindingandcache.Cache.MySharedPreferenceMovie
import com.example.movieappviewbindingandcache.Cache.MySharedPreferenceRegistration
import com.example.xarajatlarhisobi.Database.AppDatabase
import com.example.xarajatlarhisobi.Models.Registr
import com.example.xarajatlarhisobi.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    lateinit var registr: Registr

    //lateinit var appDatabaseR: AppDatabaseR
    lateinit var appDatabase: AppDatabase

    lateinit var FullListR: ArrayList<Registr>

    var N = true

    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        MySharedPreferenceMovie.init(this)

        supportActionBar!!.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        registr = Registr()
        FullListR = ArrayList()

        //appDatabaseR = AppDatabaseR.getInstance(this)
        appDatabase = AppDatabase.getInstance(this)

        //FullListR = appDatabaseR.registrDao().getAllRegistr() as ArrayList<Registr>
        FullListR = appDatabase.registrDao().getAllRegistr() as ArrayList<Registr>

        binding.btnRegister.setOnClickListener {


            for (registr in FullListR) {

                if (registr.username == binding.etUsername.text.toString() && registr.password == binding.etPassword.text.toString()) {

                    Toast.makeText(this, "Bu account mavjud", Toast.LENGTH_SHORT).show()
                    N = false
                    binding.etUsername.text!!.clear()
                    binding.etPassword.text!!.clear()
                    binding.etConfirgPassword.text!!.clear()

                }

            }

            if (N) {


                registr.username = binding.etUsername.text.toString()

                if (binding.etPassword.text.toString() == binding.etConfirgPassword.text.toString()) {

                    if (binding.etUsername.text.toString().isNotEmpty() && binding.etPassword.text!!.isNotEmpty() && binding.etConfirgPassword.text!!.isNotEmpty()){
                        registr.password = binding.etPassword.text.toString()

                        appDatabase.registrDao().addRegistr(registr)

                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                        Toast.makeText(this, "Ro'yxatdan o'tdinggiz", Toast.LENGTH_SHORT).show()
                        MySharedPreferenceMovie.user = binding.etUsername.text.toString()
                        MySharedPreferenceMovie.pass = binding.etPassword.text.toString()

                        MySharedPreferenceRegistration.NumberT = "1"

                    }else{
                        Toast.makeText(this, "Ma'lumotlarni to'ldiiring", Toast.LENGTH_SHORT).show()
                    }


                }else {

                    if (binding.etPassword.text.toString() != binding.etConfirgPassword.text.toString()) {

                        Toast.makeText(this, "Qayta parolni tekishiring", Toast.LENGTH_SHORT).show()

                        binding.etPassword.text!!.clear()
                        binding.etConfirgPassword.text!!.clear()

                    }


                }


            }


            N = true

        }


    }

    override fun onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            startActivity(Intent(this, RegistrationActivity::class.java))
            finish()
            super.onBackPressed()
        } else {
            Toast.makeText(this, "Asosiy oynaga qaytish uchun ketma ket tez bosing", Toast.LENGTH_SHORT)
                .show()
        }

        backPressedTime = System.currentTimeMillis()

    }

}