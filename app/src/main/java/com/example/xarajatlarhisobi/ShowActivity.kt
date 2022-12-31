package com.example.xarajatlarhisobi

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.xarajatlarhisobi.Database.AppDatabase
import com.example.xarajatlarhisobi.databinding.ActivityShowBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import kotlin.jvm.Throws

class ShowActivity : AppCompatActivity() {

    lateinit var binding: ActivityShowBinding
    lateinit var appDatabase: AppDatabase

    lateinit var photoURI: Uri
    lateinit var currentImagePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        appDatabase = AppDatabase.getInstance(this)

        //val id = arguments?.getInt("id")

        val id = intent.extras!!.getInt("id")
        val serializable = intent.extras!!.getString("full")

        val date = intent.extras!!.getString("date")

        val reportById = appDatabase.reportDao().getReportById(id!!)



        binding.objectName.text = reportById.objectName
        binding.productName.text = reportById.produvtName
        binding.productPrice.text = reportById.productPrice
        binding.productComment.text = reportById.productCommet

        binding.objectDate.text = date

        Toast.makeText(this, "$date", Toast.LENGTH_SHORT).show()

        binding.productImage.setImageURI(Uri.parse(serializable))

        //binding.productImage.setImageURI(Uri.fromFile(File(reportById.productImage.toString())))



    }


}