package com.example.xarajatlarhisobi

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapproomsql.Adapter.ReportAdapter
import com.example.movieappviewbindingandcache.Cache.MySharedPreferenceMovie
import com.example.movieappviewbindingandcache.Cache.MySharedPreferenceObject
import com.example.movieappviewbindingandcache.Cache.MySharedPreferenceRegistration
import com.example.xarajatlarhisobi.Database.AppDatabase
import com.example.xarajatlarhisobi.Models.ObjectMinus
import com.example.xarajatlarhisobi.Models.Report
import com.example.xarajatlarhisobi.databinding.ActivityHomeBinding
import com.example.xarajatlarhisobi.databinding.DialogMinusBinding
import com.example.xarajatlarhisobi.databinding.MyDialogBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.text.DateFormat
import java.util.Calendar

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var appDatabase: AppDatabase
    lateinit var list: ArrayList<Report>

    lateinit var reportAdapter: ReportAdapter

    private var backPressedTime = 0L

    lateinit var objectMinus: ObjectMinus
    lateinit var report: Report

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        appDatabase = AppDatabase.getInstance(this)


        if (appDatabase.reportDao().getAllReport().isEmpty()) {
            binding.textEmpty.visibility = View.VISIBLE
        } else {
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

        MySharedPreferenceMovie.init(this)
        MySharedPreferenceObject.init(this)

        list = appDatabase.reportDao().getReportByList(
            MySharedPreferenceMovie.user.toString(),
            MySharedPreferenceMovie.pass.toString()
        ) as ArrayList<Report>

        objectMinus = ObjectMinus()

        reportAdapter = ReportAdapter(
            appDatabase.reportDao(),
            list,
            object : ReportAdapter.OnMyItemClickListener {
                override fun itemClick(report: Report) {

                    val intent = Intent(this@HomeActivity, ShowActivity::class.java)
                    intent.putExtra("id", report.id)
                    intent.putExtra("full", report.productImage)
                    intent.putExtra("date", report.productDate)
                    startActivity(intent)
                    finish()


                }



                override fun itemClickAlone(report: Report) {

                    val intent = Intent(this@HomeActivity, AloneDataActivity::class.java)
                    startActivity(intent)
                    finish()

                    MySharedPreferenceObject.objectName = report.objectName
                    Log.d("ad123", "itemClickAlone:${report.objectName} ")
                    MySharedPreferenceObject.userName = report.username
                    MySharedPreferenceObject.passwordName = report.password

                }

                override fun itemCLickChange(report: Report, position: Int) {

                val dialog = AlertDialog.Builder(this@HomeActivity)
                val myDialogBinding = MyDialogBinding.inflate(layoutInflater, null, false)

                myDialogBinding.etObject.setText(report.objectName)
                myDialogBinding.etProductType.setText(report.productType)
                myDialogBinding.etProductName.setText(report.produvtName)
                myDialogBinding.etCash.setText(report.productNumber)

                dialog.setView(myDialogBinding.root)

                dialog.setPositiveButton("Edit", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                        report.objectName = myDialogBinding.etObject.text.toString()
                        report.productType = myDialogBinding.etProductType.text.toString()
                        report.produvtName = myDialogBinding.etProductName.text.toString()
                        report.productNumber = myDialogBinding.etCash.text.toString()

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


            override fun itemCLickChangeMinus(report: Report, position: Int) {

                val dialog = AlertDialog.Builder(this@HomeActivity)
                val dialogMinusBinding = DialogMinusBinding.inflate(layoutInflater, null, false)
                dialog.setView(dialogMinusBinding.root)
                val showMinus = dialog.show()

                dialogMinusBinding.dialogNoBtn.setOnClickListener {

                    showMinus.dismiss()

                }

                dialogMinusBinding.dialogYesBtn.setOnClickListener {

                    if (report.productNumber!!.toInt() >= dialogMinusBinding.etCash.text.toString()
                            .toInt()
                    ) {

                        report.productNumber =
                            (report.productNumber!!.toInt() - dialogMinusBinding.etCash.text.toString()
                                .toInt()).toString()

                        objectMinus.objectBigName = report.objectName
                        objectMinus.username = report.username
                        objectMinus.password = report.password

                        objectMinus.objectName = dialogMinusBinding.etObject.text.toString()
                        objectMinus.giveName = dialogMinusBinding.etUsername.text.toString()
                        objectMinus.cash = dialogMinusBinding.etCash.text.toString()

                        val time = Calendar.getInstance().time
                        val timeD = DateFormat.getDateInstance(DateFormat.FULL).format(time)
                        val timeF = DateFormat.getTimeInstance().format(time)

                        objectMinus.dateName = "$timeD\n$timeF"

                        MySharedPreferenceObject.init(this@HomeActivity)

                        MySharedPreferenceObject.userName = report.username
                        MySharedPreferenceObject.passwordName = report.password

                        appDatabase.objectMinus().addObjectMinus(objectMinus)


                        appDatabase.reportDao().updateReport(report)
                        list[position] = report
                        reportAdapter.notifyItemChanged(position)
                        showMinus.dismiss()


                    } else {

                        Toast.makeText(
                            this@HomeActivity,
                            "Tovar miqdoridan ko'p miqdor kiritildi",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                }

            }


            override fun itemCLickChangePlus(report: Report, position: Int) {

                reportAdapter.notifyItemChanged(position)

            }

        })

        reportAdapter.notifyItemInserted(list.size)
        reportAdapter.notifyItemRemoved(list.size)

            binding.rv.adapter = reportAdapter


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        menuInflater.inflate(R.menu.top_menu, menu)



        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        when (id) {

            R.id.usedLiteratureMenu -> {


                val builder = AlertDialog.Builder(this)

                builder.setTitle("Accountni yopish")
                builder.setMessage("Accountinggizdan chiqib ketmoqchimisiz ?")
                builder.setCancelable(false)
                builder.setPositiveButton("Ha",object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                        MySharedPreferenceObject.userName = ""
                        MySharedPreferenceObject.passwordName = ""

                        MySharedPreferenceRegistration.NumberT = "0"

                        startActivity(Intent(this@HomeActivity, RegistrationActivity::class.java))
                        finish()
                    }
                })
                builder.setNegativeButton("Yo'q",object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {


                    }

                })

                builder.show()


            }


            R.id.usedSHowMenu -> {

                startActivity(Intent(this, ObjectMinusActivity::class.java))
                finish()

/*                Toast.makeText(
                    this,
                    "${MySharedPreferenceObject.userName}\n${MySharedPreferenceObject.passwordName}",
                    Toast.LENGTH_SHORT
                ).show()*/


            }

        }

        return super.onOptionsItemSelected(item)

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