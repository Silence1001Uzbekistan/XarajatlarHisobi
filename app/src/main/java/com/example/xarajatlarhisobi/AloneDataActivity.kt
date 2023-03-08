package com.example.xarajatlarhisobi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapproomsql.Adapter.ReportThreeAdapter
import com.example.movieappviewbindingandcache.Cache.MySharedPreferenceObject
import com.example.xarajatlarhisobi.Database.AppDatabase
import com.example.xarajatlarhisobi.Models.ObjectMinus
import com.example.xarajatlarhisobi.databinding.ActivityAloneDataBinding

class AloneDataActivity : AppCompatActivity() {


    lateinit var binding: ActivityAloneDataBinding

    lateinit var appDatabase: AppDatabase

    lateinit var list: ArrayList<ObjectMinus>
    lateinit var listTwo: ArrayList<ObjectMinus>

    lateinit var reportThreeAdapter: ReportThreeAdapter

    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAloneDataBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        appDatabase = AppDatabase.getInstance(this)

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()


        list = ArrayList()
        listTwo = ArrayList()

        MySharedPreferenceObject.init(this)

/*        Toast.makeText(this, "${MySharedPreferenceObject.userName}\n${MySharedPreferenceObject.passwordName}", Toast.LENGTH_SHORT).show()*/

        list = appDatabase.objectMinus().getObjectNameByList(
            MySharedPreferenceObject.userName.toString(),
            MySharedPreferenceObject.passwordName.toString(),
            MySharedPreferenceObject.objectName.toString()
        ) as ArrayList<ObjectMinus>


        //Toast.makeText(this, "${MySharedPreferenceObject.objectName}", Toast.LENGTH_SHORT).show()

        //binding.textStatistika.text = "${MySharedPreferenceObject.objectName} di sarf xarajatlari"


        list.sortByDescending {
            it.cash!!.toInt()
        }

        reportThreeAdapter = ReportThreeAdapter(list)



        reportThreeAdapter.notifyDataSetChanged()

        binding.rv.adapter = reportThreeAdapter

    }

/*    override fun onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
            super.onBackPressed()
        } else {
            Toast.makeText(this, "Orqaga qaytish uchun ketma ket tez bosing", Toast.LENGTH_SHORT)
                .show()
        }

        backPressedTime = System.currentTimeMillis()

    }*/


}