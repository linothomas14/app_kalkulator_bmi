package com.example.soal_2hitungbmi

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val berat = et_berat
        val tinggi = et_tinggi
        val hasil = et_hasil
        val radioGroup = findViewById<RadioGroup>(R.id.rg_gender)

        btn_hitung.setOnClickListener {
            if (berat.text.toString().isNotEmpty()
                && tinggi.text.toString().isNotEmpty()                       //Check Input
            ) {
                val radioId: Int = radioGroup.checkedRadioButtonId
                val gender = findViewById<RadioButton>(radioId)
                val b: Float = berat.text.toString().toFloat()
                val t: Float = tinggi.text.toString().toFloat() / 100
                val result: Float = (b / (t * t))
                var bmi = ""
                hasil.text = "%.2f \n".format(result)
                when (gender.text) {                                        //Check Kelamin
                    "Laki-Laki" -> bmi = (t * 100 - 100).toString()
                    "Perempuan" -> bmi = ((t * 100 - 100) * 90 / 100).toString()
                }
                when (result) {                                             //Cetak Kategori BMI
                    in 0.0..18.5 -> hasil.text =
                        hasil.text.toString() + "Kekurangan berat badan \n"
                    in 18.6..24.9 -> hasil.text = hasil.text.toString() + "Normal (Ideal)\n"
                    in 25.0..29.9 -> hasil.text =
                        hasil.text.toString() + "Kelebihan berat badan\n"
                    else -> hasil.text = hasil.text.toString() + "Kegemukan (Obesitas)\n"
                }
                hasil.text = hasil.text.toString() + "Berat Badan Idealmu adalah $bmi"          //Cetak Berat Badan Ideal
            } else Toast.makeText(this, "Masukkan inputan dengan benar!!", Toast.LENGTH_LONG).show()
        }
    }
}