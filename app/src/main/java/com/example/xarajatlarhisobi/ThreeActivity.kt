package com.example.xarajatlarhisobi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapproomsql.Adapter.ReportTwoAdapter
import com.example.movieappviewbindingandcache.Cache.MySharedPreferenceMovie
import com.example.xarajatlarhisobi.Database.AppDatabase
import com.example.xarajatlarhisobi.Models.Report
import com.example.xarajatlarhisobi.databinding.ActivityThreeBinding

class ThreeActivity : AppCompatActivity() {

    lateinit var binding: ActivityThreeBinding

    lateinit var appDatabase: AppDatabase
    lateinit var list: ArrayList<Report>
    lateinit var listTwo: ArrayList<Report>

    lateinit var reportTwoAdapter: ReportTwoAdapter

    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThreeBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        appDatabase = AppDatabase.getInstance(this)


        binding.bottomNavigationView.selectedItemId = R.id.statistikaMenu

        binding.bottomNavigationView.setOnItemSelectedListener {

            val id = it.itemId

            when (id) {


                R.id.homeMenu -> {

                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                    finish()


                }

                R.id.calculateMenu -> {

                    startActivity(Intent(applicationContext, OneActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                    finish()

                }

                R.id.plusMenu-> {

                    startActivity(Intent(applicationContext, TwoActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                    finish()

                }


                R.id.statistikaMenu -> {

                    true

                }


                R.id.infoMenu -> {


                    startActivity(Intent(applicationContext, FourActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                    finish()

                }

            }


            false
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()


        list = ArrayList()
        listTwo = ArrayList()

        MySharedPreferenceMovie.init(this)

        list = appDatabase.reportDao().getReportByList(
            MySharedPreferenceMovie.user.toString(),
            MySharedPreferenceMovie.pass.toString()
        ) as ArrayList<Report>


        list.sortByDescending {
            it.productPrice!!.toInt()
        }

        reportTwoAdapter = ReportTwoAdapter(list)


        if (list.size > 3) {

            binding.lavOne.visibility = View.INVISIBLE

        }

        reportTwoAdapter.notifyDataSetChanged()

        binding.rv.adapter = reportTwoAdapter

    }

    override fun onBackPressed() {

        if (backPressedTime + 3000 > System.currentTimeMillis()) {
            finish()
            super.onBackPressed()
        } else {
            Toast.makeText(this, "Dasturdan chiqish uchun ketma ket tez bosing", Toast.LENGTH_SHORT)
                .show()
        }

        backPressedTime = System.currentTimeMillis()

    }


}