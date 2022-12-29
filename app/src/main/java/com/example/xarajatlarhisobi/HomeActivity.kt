package com.example.xarajatlarhisobi

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.movieapproomsql.Adapter.ReportAdapter
import com.example.xarajatlarhisobi.Database.AppDatabase
import com.example.xarajatlarhisobi.Models.Report
import com.example.xarajatlarhisobi.databinding.ActivityHomeBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var appDatabase: AppDatabase
    lateinit var list: ArrayList<Report>

    lateinit var reportAdapter: ReportAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        appDatabase = AppDatabase.getInstance(this)

        list = ArrayList()


        supportActionBar!!.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding.bottomNavigationView.selectedItemId = R.id.homeMenu

        binding.bottomNavigationView.setOnItemSelectedListener {

            val id = it.itemId

            when (id) {


                R.id.homeMenu -> {

                    true


                }

                R.id.calculateMenu -> {

                    startActivity(Intent(applicationContext, OneActivity::class.java))
                    overridePendingTransition(0, 0)
                    true

                }

                R.id.plusMenu -> {

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

        list = appDatabase.citizenDao().getAllReport() as ArrayList<Report>

        reportAdapter = ReportAdapter(list, object : ReportAdapter.OnMyItemClickListener {
            override fun itemClick(report: Report) {

/*                val bundle = Bundle()
                bundle.putInt("id", movie.id!!)

                findNavController().navigate(R.id.showFragment, bundle)*/

            }

            override fun itemCLickChange(report: Report, position: Int) {

/*
                val bundle = Bundle()
                bundle.putInt("id", movie.id!!)
                bundle.putInt("a", position)


                val dialog = AlertDialog.Builder(context)
                val myDialogBinding = MyDialogBinding.inflate(layoutInflater, null, false)

                myDialogBinding.nameEt.setText(movie.movieName)
                myDialogBinding.authorsEt.setText(movie.movieAuthors)
                myDialogBinding.aboutEt.setText(movie.movieAbout)
                myDialogBinding.dateEt.setText(movie.movieDate)

                dialog.setView(myDialogBinding.root)

                dialog.setPositiveButton("Edit", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                        movie.movieName = myDialogBinding.nameEt.text.toString()
                        movie.movieAuthors = myDialogBinding.authorsEt.text.toString()
                        movie.movieAbout = myDialogBinding.aboutEt.text.toString()
                        movie.movieDate = myDialogBinding.dateEt.text.toString()

                        appDatabase.movieDao().updateMovie(movie)
                        list[position] = movie
                        movieAdapter.notifyItemChanged(position)

                    }

                })

                dialog.show()
*/

            }

            override fun itemClickDelete(report: Report, position: Int) {

/*                appDatabase.movieDao().deleteMovie(movie)
                list.remove(movie)
                movieAdapter.notifyItemRemoved(list.size)
                movieAdapter.notifyItemRangeRemoved(position, list.size)*/

            }

        })

        reportAdapter.notifyItemInserted(list.size)
        reportAdapter.notifyItemRemoved(list.size)

        binding.rv.adapter = reportAdapter

    }


}