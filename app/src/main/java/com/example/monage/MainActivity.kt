package com.example.monage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
        getTotal3()
        BottomSheetBehavior.from(standar_bottom_sheet).apply {
            peekHeight = 180
            this.state = BottomSheetBehavior.STATE_COLLAPSED
            standar_bottom_sheet.bringToFront()
        }

        btn_pemasukan.setOnClickListener{
            val intent = Intent(this, PemasukanActivity::class.java)
            startActivity(intent)
        }

        btn_pengeluaran.setOnClickListener{
            val intent = Intent(this, PengeluaranActivity::class.java)
            startActivity(intent)
        }
        btnrefresh.setOnClickListener{
            getData()
            getTotal3()
        }

    }
    private fun getTotal3(){
        val databaseHandler: DatabaseHelper = DatabaseHelper(this)
        val db = databaseHandler.readableDatabase
        var rs2 = db.rawQuery("SELECT SUM(saldo) FROM monage_database WHERE aksi=2",null)
        var rs = db.rawQuery("SELECT SUM(saldo) FROM monage_database WHERE aksi=1",null)
        if(rs.moveToFirst()&&rs2.moveToFirst()){
            var saldo2 = rs2.getString(0)
            var saldo1 = rs.getString(0)
            if (saldo1!=null && saldo2==null) {
                var rs3: Int = saldo1.toInt() - 0
                total_uang.setText("Rp " + rs3.toString())
                total_pengeluaran.setText("Rp 0")
                total_pemasukan.setText("Rp " + rs.getString(0))
            }
            else if (saldo1==null && saldo2!=null) {
                var rs3: Int = 0 - saldo2.toInt()
                total_uang.setText("Rp " + rs3.toString())
                total_pengeluaran.setText("Rp "+ rs2.getString(0))
                total_pemasukan.setText("Rp 0")
            }
            else{
                total_uang.setText("Rp 0")
                total_pengeluaran.setText("Rp 0")
                total_pemasukan.setText("Rp 0")
            }
        }
    }
    private fun getData() {
        val databaseHandler: DatabaseHelper = DatabaseHelper(this)
        val salList2 = databaseHandler.getAllSaldo(this)
        val adapter = SaldoAdapter(this,salList2)
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = adapter
    }

}