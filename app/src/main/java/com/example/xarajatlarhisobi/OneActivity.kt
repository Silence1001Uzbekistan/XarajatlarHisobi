package com.example.xarajatlarhisobi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.xarajatlarhisobi.databinding.ActivityOneBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.math.BigDecimal

class OneActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var binding: ActivityOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOneBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)


        binding.bottomNavigationView.selectedItemId = R.id.calculateMenu

        binding.bottomNavigationView.setOnItemSelectedListener {

            val id = it.itemId

            when (id) {


                R.id.homeMenu -> {

                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                    overridePendingTransition(0, 0)
                    true


                }

                R.id.calculateMenu -> {


                    true

                }

                R.id.plusMenu-> {

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

        binding.btnClear.setOnClickListener(this)
        binding.btnOpen.setOnClickListener(this)
        binding.btnClose.setOnClickListener(this)
        binding.btnSlash.setOnClickListener(this)

        binding.btn7.setOnClickListener(this)
        binding.btn8.setOnClickListener(this)
        binding.btn9.setOnClickListener(this)
        binding.btnX.setOnClickListener(this)

        binding.btn4.setOnClickListener(this)
        binding.btn5.setOnClickListener(this)
        binding.btn6.setOnClickListener(this)
        binding.btnMinus.setOnClickListener(this)

        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        binding.btnPlus.setOnClickListener(this)

        binding.btnPoint.setOnClickListener(this)
        binding.btn0.setOnClickListener(this)
        binding.btnBack.setOnClickListener(this)
        binding.btnAnswer.setOnClickListener(this)


    }

    override fun onClick(p0: View?) {

        val id = p0?.id

        when (id) {

            R.id.btn_0 -> {
                if (binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "0"
                ) {
                    emptyAdd()
                    binding.tvAnswer.append("0")
                }
            }

            R.id.btn_1 -> {
                emptyAdd()
                binding.tvAnswer.append("1")
            }

            R.id.btn_2 -> {
                emptyAdd()
                binding.tvAnswer.append("2")
            }

            R.id.btn_3 -> {
                emptyAdd()
                binding.tvAnswer.append("3")
            }

            R.id.btn_4 -> {
                emptyAdd()
                binding.tvAnswer.append("4")
            }

            R.id.btn_5 -> {
                emptyAdd()
                binding.tvAnswer.append("5")
            }

            R.id.btn_6 -> {
                emptyAdd()
                binding.tvAnswer.append("6")
            }

            R.id.btn_7 -> {
                emptyAdd()
                binding.tvAnswer.append("7")
            }

            R.id.btn_8 -> {
                emptyAdd()
                binding.tvAnswer.append("8")
            }

            R.id.btn_9 -> {
                emptyAdd()
                binding.tvAnswer.append("9")
            }

            R.id.btn_close -> {
                if (binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "." && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "(" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != ")" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "*" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "-" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "+" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "/"
                ) {
                    emptyAdd()
                    binding.tvAnswer.append(")")
                }
            }


            R.id.btn_minus -> {
                if (binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "." && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "(" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != ")" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "*" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "-" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "+" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "/"
                ) {
                    emptyAdd()
                    binding.tvAnswer.append("-")
                }
            }

            R.id.btn_plus -> {
                if (binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "." && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "(" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != ")" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "*" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "-" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "+" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "/"
                ) {
                    emptyAdd()
                    binding.tvAnswer.append("+")
                }
            }

            R.id.btn_open -> {
                if (binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "." && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "(" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != ")" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "*" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "-" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "+" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "/"
                ) {
                    emptyAdd()
                    binding.tvAnswer.append("(")
                }
            }

            R.id.btn_slash -> {
                if (binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "." && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "(" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != ")" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "*" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "-" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "+" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "/"
                ) {
                    emptyAdd()
                    binding.tvAnswer.append("/")
                }
            }

            R.id.btn_x -> {
                if (binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "." && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "(" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != ")" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "*" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "-" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "+" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "/"
                ) {
                    emptyAdd()
                    binding.tvAnswer.append("*")
                }

            }

            R.id.btn_point -> {
                if (binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "." && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "(" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != ")" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "*" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "-" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "+" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "/"
                ) {
                    binding.tvAnswer.append(".")
                }

            }


            R.id.btn_answer -> {

                //Toast.makeText(this, binding.tvAnswer.text.substring(binding.tvAnswer.text.length-1,binding.tvAnswer.length()), Toast.LENGTH_SHORT).show()

                if (binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "." && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "(" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != ")" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "*" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "-" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "+" && binding.tvAnswer.text.substring(
                        binding.tvAnswer.text.length - 1,
                        binding.tvAnswer.text.length
                    ) != "/"
                ) {
                    var build = ExpressionBuilder(binding.tvAnswer.text.toString()).build()
                    binding.tvAnswer.text = BigDecimal(build.evaluate()).toString()
                } else {
                    Toast.makeText(this, "Bajarilmadi", Toast.LENGTH_SHORT).show()
                }

            }

            R.id.btn_clear -> {
                binding.tvAnswer.text = "0"

            }


            R.id.btn_back -> {
                if (binding.tvAnswer.text != "0" && binding.tvAnswer.text.isNotEmpty() && binding.tvAnswer.text.length != 1) {
                    val text = binding.tvAnswer.text
                    binding.tvAnswer.text =
                        binding.tvAnswer.text.subSequence(0, binding.tvAnswer.text.length - 1)
                } else {
                    if (binding.tvAnswer.text.length == 1) {
                        binding.tvAnswer.text = "0"
                    }
                }

            }

        }
    }

    private fun emptyAdd() {

        val text = binding.tvAnswer.text.toString()

        if (text == "0") {
            binding.tvAnswer.text = ""
        }

    }

}