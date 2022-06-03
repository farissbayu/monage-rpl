package com.example.monage

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.activity_pemasukan.*
import kotlinx.android.synthetic.main.activity_pengeluaran.*

class PemasukanActivity : AppCompatActivity() {

    lateinit var input_tanggal: EditText
    lateinit var input_nominal: EditText
    lateinit var input_kategori: EditText
    lateinit var button_simpan: Button
    val aksi: Int = 1

    //private lateinit var tvDatePicker: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pemasukan)

        input_tanggal = findViewById(R.id.pilih_tanggal_pemasukan)
        input_nominal = findViewById(R.id.nominal_pemasukan)
        input_kategori = findViewById(R.id.kategori_pemasukan)
        button_simpan = findViewById(R.id.simpan_pemasukan)


        simpan_pemasukan.setOnClickListener{ view ->
            addRecord(view)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addRecord(view: View){
        val nmnl = Integer.valueOf(nominal_pemasukan.text.toString())
        val ktgr = kategori_pemasukan.text.toString()
        val tggl = pilih_tanggal_pemasukan.text.toString()
        val databaseHandler: DatabaseHelper = DatabaseHelper(this)

        if (!tggl.isEmpty() && !ktgr.isEmpty()) {
            val status =
                databaseHandler.addSaldo(SaldoModelClass(0, nmnl, 1, ktgr, tggl))
            if (status > -1) {
                Toast.makeText(applicationContext, "Berhasil ditambahkan", Toast.LENGTH_LONG).show()
                nominal_pemasukan.text.clear()
                kategori_pemasukan.text.clear()
                pilih_tanggal_pemasukan.text.clear()
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