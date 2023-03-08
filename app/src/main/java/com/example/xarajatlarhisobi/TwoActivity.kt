package com.example.xarajatlarhisobi

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.movieappviewbindingandcache.Cache.MySharedPreferenceMovie
import com.example.xarajatlarhisobi.Database.AppDatabase
import com.example.xarajatlarhisobi.Models.Report
import com.example.xarajatlarhisobi.databinding.ActivityTwoBinding
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class TwoActivity : AppCompatActivity() {

    lateinit var binding: ActivityTwoBinding
    lateinit var appDatabase: AppDatabase

    lateinit var photoURI: Uri
    lateinit var currentImagePath: String

    private var backPressedTime = 0L

    lateinit var report: Report


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTwoBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)


        appDatabase = AppDatabase.getInstance(this)



        binding.bottomNavigationView.selectedItemId = R.id.plusMenu

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

                R.id.plusMenu -> {

                    true

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

        report = Report()

        val arrayAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.viloyat1,
            R.layout.color_spinner_layout
        )

        val arrayAdapterTwo = ArrayAdapter.createFromResource(
            this,
            R.array.uzunlik,
            R.layout.color_spinner_layout
        )

        arrayAdapter.setDropDownViewResource(R.layout.spinner_drop_down_layout)
        arrayAdapterTwo.setDropDownViewResource(R.layout.spinner_drop_down_layout)

        binding.productTypeId.adapter = arrayAdapter
        binding.productLengthSpinner.adapter = arrayAdapterTwo

        //spinnerda tanlangani bo'yicha ishlash mumkin
        binding.productTypeId.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {


                report.productType = p0!!.getItemAtPosition(p2).toString()
/*                Log.d("don", "onItemSelected: $p0")
                Toast.makeText(this@TwoActivity, "${p0!!.getItemAtPosition(p2).toString()}", Toast.LENGTH_SHORT).show()*/

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {


            }

        }

        //spinnerda tanlangani bo'yicha ishlash mumkin
        binding.productLengthSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {


                report.productLength = p0!!.getItemAtPosition(p2).toString()
/*                Log.d("don", "onItemSelected: $p0")
                Toast.makeText(this@TwoActivity, "${p0!!.getItemAtPosition(p2).toString()}", Toast.LENGTH_SHORT).show()*/

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {


            }

        }

        val allReport = appDatabase.reportDao().getAllReport()


        var N = false




        binding.productSave.setOnClickListener {

            if (binding.objectNameId.text.trim().isNotEmpty() && binding.productNameId.text.trim()
                    .isNotEmpty() && binding.productPriceId.text.trim()
                    .isNotEmpty() && binding.studentDateEt.text.trim().isNotEmpty()
            ) {

                report.username = MySharedPreferenceMovie.user
                report.password = MySharedPreferenceMovie.pass

                report.objectName = binding.objectNameId.text.toString()
                //report.productType = binding.productTypeId.selectedItemPosition
                report.produvtName = binding.productNameId.text.toString()
                report.productPrice = binding.productPriceId.text.toString()

                report.productNumber = binding.productNumberId.text.toString()

                report.productCommet = binding.productCommentId.text.toString()


                report.productImage = Uri.fromFile(File(currentImagePath)).toString()

                report.productDate = binding.studentDateEt.text.toString().trim()

                for (report in allReport) {

                    if (report.objectName == binding.objectNameId.text.toString()) {

                        N = true


                    }

                }

                if (!N) {

                    appDatabase.reportDao().addReport(report)

                } else {

                    val byId =
                        appDatabase.reportDao().getCitizenById(binding.objectNameId.text.toString())

                    Log.d("AppDebug", byId.toString())
                    report.id = byId
                    report.objectName = binding.objectNameId.text.toString()
                    //report.productType = binding.productTypeId.selectedItemPosition
                    report.produvtName = binding.productNameId.text.toString()
                    report.productPrice = (appDatabase.reportDao().getReportById(byId).productPrice!!.toInt() + binding.productPriceId.text.toString().toInt()).toString()

                    report.productCommet = binding.productCommentId.text.toString()

                    Toast.makeText(this, "${appDatabase.reportDao().getReportById(byId).productPrice}", Toast.LENGTH_SHORT).show()

                    appDatabase.reportDao().updateReport(report)

                }




                startActivity(Intent(applicationContext, HomeActivity::class.java))
                finish()

                Snackbar.make(it, "Ma'lumotlar kiritildi", 1500).show()


            } else {

                Snackbar.make(it, "Zarur ma'lumotlarni kiriting", 3000).show()

            }

        }

        binding.studentDateEt.setOnClickListener {


            var dataPickerDialog = DatePickerDialog(this)
            dataPickerDialog.setOnDateSetListener { p0, p1, p2, p3 ->
                binding.studentDateEt.text = "$p3.${p2 + 1}.$p1"
            }
            dataPickerDialog.show()

        }




    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onResume() {
        super.onResume()

        binding.productImageId.setOnClickListener {


            val imageFile = createImageFile()
            photoURI = FileProvider.getUriForFile(
                this,
                com.example.xarajatlarhisobi.BuildConfig.APPLICATION_ID,
                imageFile
            )

            getTakeImageContent.launch(photoURI)

            binding.productSave.visibility = View.VISIBLE
            binding.textSave.visibility = View.GONE

        }





    }

    private val getTakeImageContent =
        registerForActivityResult(ActivityResultContracts.TakePicture()) {

            if (it) {
                binding.productImageId.setImageURI(photoURI)
                val openInputStream = contentResolver?.openInputStream(photoURI)
                val file = File(filesDir, "image.jpg")
                val fileOutputStream = FileOutputStream(file)
                openInputStream?.copyTo(fileOutputStream)
                openInputStream?.close()
                fileOutputStream.close()

                val fileAbsolutePath = file.absolutePath
                //Toast.makeText(this, fileAbsolutePath, Toast.LENGTH_SHORT).show()
            }

        }

    @SuppressLint("NewApi")
    @Throws(IOException::class)
    private fun createImageFile(): File {

        val format =
            android.icu.text.SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile("JPEG_$format", ".jpg", externalFilesDir).apply {
            currentImagePath = absolutePath
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (::currentImagePath.isInitialized) {
            binding.productImageId.setImageURI(Uri.fromFile(File(currentImagePath)))
        }

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