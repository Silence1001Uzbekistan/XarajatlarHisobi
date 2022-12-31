package com.example.xarajatlarhisobi

import android.Manifest
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.navigation.fragment.findNavController
import com.example.xarajatlarhisobi.Database.AppDatabase
import com.example.xarajatlarhisobi.Models.Report
import com.example.xarajatlarhisobi.databinding.ActivityTwoBinding
import com.google.android.material.snackbar.Snackbar
import com.karumi.dexter.BuildConfig
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.jvm.Throws

class TwoActivity : AppCompatActivity() {

    lateinit var binding: ActivityTwoBinding
    lateinit var appDatabase: AppDatabase

    lateinit var photoURI: Uri
    lateinit var currentImagePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTwoBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)


        supportActionBar!!.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding.bottomNavigationView.selectedItemId = R.id.plusMenu

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

                R.id.plusMenu -> {

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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onResume() {
        super.onResume()

        var report = Report()



        appDatabase = AppDatabase.getInstance(this)

        binding.productImageId.setOnClickListener {


            val imageFile = createImageFile()
            photoURI = FileProvider.getUriForFile(
                this,
                com.example.xarajatlarhisobi.BuildConfig.APPLICATION_ID,
                imageFile
            )

            getTakeImageContent.launch(photoURI)

            binding.productSave.visibility = View.VISIBLE

        }




        binding.productSave.setOnClickListener {

            if (binding.objectNameId.text.trim().isNotEmpty() && binding.productNameId.text.trim()
                    .isNotEmpty() && binding.productPriceId.text.trim()
                    .isNotEmpty() && binding.studentDateEt.text.trim().isNotEmpty()
            ) {

                report.objectName = binding.objectNameId.text.toString()
                report.productType = binding.productTypeId.selectedItemPosition
                report.produvtName = binding.productNameId.text.toString()
                report.productPrice = binding.productPriceId.text.toString()
                report.productCommet = binding.productCommentId.text.toString()


                report.productImage = Uri.fromFile(File(currentImagePath)).toString()

                report.productDate = binding.studentDateEt.text.toString().trim()


                appDatabase.reportDao().addReport(report)


                startActivity(Intent(applicationContext, HomeActivity::class.java))

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
                Toast.makeText(this, fileAbsolutePath, Toast.LENGTH_SHORT).show()
            }

        }

    @SuppressLint("NewApi")
    @Throws(IOException::class)
    private fun createImageFile(): File {

        val format = android.icu.text.SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
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


}