package com.example.monage

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pemasukan.*
import kotlinx.android.synthetic.main.activity_pengeluaran.*
import java.text.SimpleDateFormat
import java.util.*

class PengeluaranActivity : AppCompatActivity() {

    lateinit var input_tanggal: EditText
    lateinit var input_nominal: EditText
    lateinit var input_kategori: EditText
    lateinit var button_simpan: Button
    val aksi: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengeluaran)

        simpan_pengeluaran.setOnClickListener{ view ->
            addRecord(view)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addRecord(view: View){
        val nmnl = Integer.valueOf(nominal_pengeluaran.text.toString())
        val ktgr = kategori_pengeluaran.text.toString()
        val tggl = pilih_tanggal_pengeluaran.text.toString()
        val databaseHandler: DatabaseHelper = DatabaseHelper(this)

        if (!tggl.isEmpty() && !ktgr.isEmpty()) {
            val status =
                databaseHandler.addSaldo(SaldoModelClass(0, nmnl, 2, ktgr, tggl))
            if (status > -1) {
                Toast.makeText(applicationContext, "Berhasil ditambahkan", Toast.LENGTH_LONG).show()
                nominal_pengeluaran.text.clear()
                kategori_pengeluaran.text.clear()
                pilih_tanggal_pengeluaran.text.clear()
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Gagal ditambahkan",
                Toast.LENGTH_LONG
            ).show()
        }

    }
}