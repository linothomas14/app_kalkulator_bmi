package com.example.soal_2hitungbmi

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (et_tinggi.getText().toString().trim().equals("")) {
            btn_hitung.setEnabled(false)
        } else {
            btn_hitung.setEnabled(true)
        }
        et_berat.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().trim({ it <= ' ' }).isEmpty()) {
                    et_tinggi.setEnabled(false)
                } else {
                    et_tinggi.setEnabled(true)
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })
        et_tinggi.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().trim({ it <= ' ' }).isEmpty()) {
                    btn_hitung.setEnabled(false)
                } else {
                    btn_hitung.setEnabled(true)
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })

        btn_hitung.setOnClickListener {
            val tinggi = et_tinggi.text.toString()
            val berat = et_berat.text.toString()
            val hasil = et_hasil
            val check = rg_gender.checkedRadioButtonId
            val gender = findViewById<RadioButton>(check)
            try {
                val berat: Float = berat.toFloat()
                val tinggi: Float = tinggi.toFloat()

                val result: Float = (berat / (tinggi / 100 * tinggi / 100))
                var BMI: Int? = 0
                hasil.text = "%.2f \n".format(result)

                when (gender.text) { //Check Kelamin
                    "Laki-Laki" -> BMI = (tinggi - 100).toInt()
                    "Perempuan" -> BMI = ((tinggi - 100) * 90 / 100).toInt()
                }
                 when (result) {
                    in 0.0..18.5 -> hasil.text = hasil.text.toString() + "Kekurangan berat badan \n"
                    in 18.6..24.9 -> hasil.text = hasil.text.toString() + "Normal (Ideal)\n"
                    in 25.0..29.9 -> hasil.text = hasil.text.toString() + "Kelebihan berat badan\n"
                    else -> hasil.text = hasil.text.toString() + "Kegemukan (Obesitas)\n"
                }
                hasil.text = hasil.text as String + "Berat Badan Idealmu adalah $BMI"
            } catch (e: Exception) {
                Toast.makeText(this, "Pilih jenis kelamin", Toast.LENGTH_LONG).show()
            }
        }
    }
}
