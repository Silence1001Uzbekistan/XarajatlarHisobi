package com.example.xarajatlarhisobi

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapproomsql.Adapter.ReportAdapter
import com.example.xarajatlarhisobi.Database.AppDatabase
import com.example.xarajatlarhisobi.Models.Report
import com.example.xarajatlarhisobi.databinding.ActivityHomeBinding
import com.example.xarajatlarhisobi.databinding.MyDialogBinding
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

    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        appDatabase = AppDatabase.getInstance(this)


        if (appDatabase.reportDao().getAllReport().isEmpty()){
            binding.textEmpty.visibility = View.VISIBLE
        }else{
            binding.textEmpty.visibility = View.INVISIBLE
        }


//        Toast.makeText(this@HomeActivity, "${appDatabase.reportDao().getAllReport().size}", Toast.LENGTH_SHORT).show()

        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {

/*                    Toast.makeText(
                        this@HomeActivity,
                        "Rasmlarga kirishga ruxsat berildi",
                        Toast.LENGTH_SHORT
                    ).show()*/


                    if (appDatabase.reportDao().getAllReport().isEmpty()){
                        binding.textEmpty.visibility = View.VISIBLE
                    }else{
                        binding.textEmpty.visibility = View.INVISIBLE
                    }

/*                    Toast.makeText(this@HomeActivity, "${appDatabase.reportDao().getAllReport().size}", Toast.LENGTH_SHORT).show()*/


                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken?
                ) {

                    Toast.makeText(
                        this@HomeActivity,
                        "Rasmlarga kirishga ruxsat bermadinggiz,dasturni o'chirib qayta ruxsat berishni tavsiya qilamiz ",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()

                }
            }).check()




        list = ArrayList()


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
                    finish()

                }

                R.id.plusMenu -> {

                    startActivity(Intent(applicationContext, TwoActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                    finish()

                }


                R.id.statistikaMenu -> {

                    startActivity(Intent(applicationContext, ThreeActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                    finish()

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

    override fun onResume() {
        super.onResume()

        list = appDatabase.reportDao().getAllReport() as ArrayList<Report>

        reportAdapter = ReportAdapter(list, object : ReportAdapter.OnMyItemClickListener {
            override fun itemClick(report: Report) {

                val intent = Intent(this@HomeActivity, ShowActivity::class.java)
                intent.putExtra("id", report.id)
                intent.putExtra("full",report.productImage)
                intent.putExtra("date",report.productDate)
                startActivity(intent)


            }

            override fun itemCLickChange(report: Report, position: Int) {

                val dialog = AlertDialog.Builder(this@HomeActivity)
                val myDialogBinding = MyDialogBinding.inflate(layoutInflater, null, false)

                myDialogBinding.nameEt.setText(report.objectName)
                myDialogBinding.authorsEt.setText(report.produvtName)
                myDialogBinding.aboutEt.setText(report.productCommet)
                myDialogBinding.dateEt.setText(report.productPrice)

                dialog.setView(myDialogBinding.root)

                dialog.setPositiveButton("Edit", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                        report.objectName = myDialogBinding.nameEt.text.toString()
                        report.produvtName = myDialogBinding.authorsEt.text.toString()
                        report.productCommet = myDialogBinding.aboutEt.text.toString()
                        report.productPrice = myDialogBinding.dateEt.text.toString()

                        appDatabase.reportDao().updateReport(report)
                        list[position] = report
                        reportAdapter.notifyItemChanged(position)

                    }

                })

                dialog.show()

            }

            override fun itemClickDelete(report: Report, position: Int) {


                val builder = AlertDialog.Builder(this@HomeActivity)

                builder.setTitle("Ma'lumot o'chiriladi")
                builder.setMessage("Bu ma'lumotni o'chirib tashlamoqchimisiz ?")
                builder.setCancelable(false)
                builder.setPositiveButton("Ha",object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        appDatabase.reportDao().deleteReport(report)
                        list.remove(report)
                        reportAdapter.notifyItemRemoved(list.size)
                        reportAdapter.notifyItemRangeRemoved(position, list.size)


                        if (appDatabase.reportDao().getAllReport().isEmpty()){
                            binding.textEmpty.visibility = View.VISIBLE
                        }else{
                            binding.textEmpty.visibility = View.INVISIBLE
                        }

                    }
                })
                builder.setNegativeButton("Yo'q",object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {


                    }

                })

                builder.show()


            }

        })

        reportAdapter.notifyItemInserted(list.size)
        reportAdapter.notifyItemRemoved(list.size)

        if (list[0].username!!.isNotEmpty()) {
            binding.rv.adapter = reportAdapter
        }


    }

    override fun onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finish()
            super.onBackPressed()
        } else {
            Toast.makeText(this, "Dasturdan chiqish uchun ketma ket tez bosing", Toast.LENGTH_SHORT)
                .show()
        }

        backPressedTime = System.currentTimeMillis()

    }

}