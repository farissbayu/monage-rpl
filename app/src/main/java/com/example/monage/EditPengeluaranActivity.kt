package com.example.monage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_pemasukan.*
import kotlinx.android.synthetic.main.activity_edit_pengeluaran.*

class EditPengeluaranActivity : AppCompatActivity() {
    private var xId: String? = null
    private var xKat: String? = null
    private var xTgl: String? = null
    private var xNml: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_pengeluaran)
        val terima = intent
        xId = terima.getStringExtra("xId")
        xKat = terima.getStringExtra("xKat")
        xTgl = terima.getStringExtra("xTgl")
        xNml = terima.getStringExtra("xNml")
        idKat2.setText(xId)
        kategori_pengeluaran.setText(xKat)
        pilih_tanggal_pengeluaran.setText(xTgl)
        nominal_pengeluaran.setText(xNml)
        edit_pengeluaran.setOnClickListener{ view->
            updateRecord(view)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        hapus_pengeluaran.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun updateRecord(view: View) {
        val id = idKat2.text.toString()
        val nmnl = Integer.valueOf(nominal_pengeluaran.text.toString())
        val ktgr = kategori_pengeluaran.text.toString()
        val tggl = pilih_tanggal_pengeluaran.text.toString()
        val databaseHandler: DatabaseHelper = DatabaseHelper(this)

        val status =
            databaseHandler.editSaldo(id, ktgr, tggl, nmnl)
        if (status==true) {
            Toast.makeText(this,"Update Berhasil", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Update Gagal", Toast.LENGTH_SHORT).show()
        }
    }
}