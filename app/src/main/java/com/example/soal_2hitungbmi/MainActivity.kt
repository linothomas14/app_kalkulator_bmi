package com.example.soal_2hitungbmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var berat = findViewById<EditText>(R.id.et_berat)
        var tinggi = findViewById<EditText>(R.id.et_tinggi)
        var btn_hitung = findViewById<Button>(R.id.btn_hitung)
        var hasil = findViewById<TextView>(R.id.et_hasil)
        var ket = findViewById<TextView>(R.id.et_ket)

        btn_hitung.setOnClickListener {
            val check = rg_gender.checkedRadioButtonId
            val gender = findViewById<RadioButton>(check)
            val berat: Float = berat.text.toString().toFloat()
            val tinggi: Float = tinggi.text.toString().toFloat()
            val result: Float = (berat / (tinggi / 100 * tinggi / 100))
            var BMI: Int = 0
            hasil.text = "%.2f".format(result)

            when (gender.text) { //Check Kelamin
                "Laki-Laki" -> BMI = (tinggi - 100).toInt()
                "Perempuan" -> BMI = ((tinggi - 100) * 90 / 100).toInt()
            }
            if (result < 18.5) {  //Check keterangan
                ket.text = "Kekurangan berat badan"
            } else if (result < 24.9) {
                ket.text = "Normal (Ideal)"
            } else if (result < 29.9) {
                ket.text = "Kelebihan berat badan"
            } else {
                ket.text = "Kegemukan (Obesitas)"
            }
            ket.text = ket.text as String + " \n Berat Badan Idealmu adalah $BMI"
        }
    }


}
