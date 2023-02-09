package com.example.xarajatlarhisobi

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movieappviewbindingandcache.Cache.MySharedPreferenceMovie
import com.example.xarajatlarhisobi.Database.AppDatabaseR
import com.example.xarajatlarhisobi.Models.Registr
import com.example.xarajatlarhisobi.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    lateinit var registr: Registr

    lateinit var appDatabaseR: AppDatabaseR

    lateinit var FullListR: ArrayList<Registr>

    var N = true

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

        appDatabaseR = AppDatabaseR.getInstance(this)

        FullListR = appDatabaseR.registrDao().getAllRegistr() as ArrayList<Registr>

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

                if (binding.etPassword.text == binding.etConfirgPassword){

                    registr.password = binding.etPassword.text.toString()

                    appDatabaseR.registrDao().addRegistr(registr)
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                    Toast.makeText(this, "Ro'yxatdan o'tdinggiz", Toast.LENGTH_SHORT).show()
                    MySharedPreferenceMovie.user = binding.etUsername.text.toString()
                    MySharedPreferenceMovie.pass = binding.etPassword.text.toString()

                }else{

                    Toast.makeText(this, "Qayta parolni tekishiring", Toast.LENGTH_SHORT).show()

                    binding.etPassword.text!!.clear()
                    binding.etConfirgPassword.text!!.clear()

                }


            }


            N = true

        }


    }
}