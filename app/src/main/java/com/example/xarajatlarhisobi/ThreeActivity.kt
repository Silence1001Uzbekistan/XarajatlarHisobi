package com.example.xarajatlarhisobi

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.example.movieapproomsql.Adapter.ReportAdapter
import com.example.movieapproomsql.Adapter.ReportTwoAdapter
import com.example.xarajatlarhisobi.Database.AppDatabase
import com.example.xarajatlarhisobi.Models.Report
import com.example.xarajatlarhisobi.databinding.ActivityHomeBinding
import com.example.xarajatlarhisobi.databinding.ActivityThreeBinding
import com.example.xarajatlarhisobi.databinding.MyDialogBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.math.max

class ThreeActivity : AppCompatActivity() {

    lateinit var binding: ActivityThreeBinding

    lateinit var appDatabase: AppDatabase
    lateinit var list: ArrayList<Report>
    lateinit var listTwo: ArrayList<Report>

    lateinit var reportTwoAdapter: ReportTwoAdapter

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

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()


        list = ArrayList()
        listTwo = ArrayList()

        list = appDatabase.reportDao().getAllReport() as ArrayList<Report>

        list.sortedBy {
            it.productPrice
        }

        reportTwoAdapter = ReportTwoAdapter(list)

        reportTwoAdapter.notifyDataSetChanged()

        binding.rv.adapter = reportTwoAdapter

    }




}