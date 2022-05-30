package com.example.monage

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BottomSheetBehavior.from(standar_bottom_sheet).apply {
            peekHeight = 180
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        btn_pemasukan.setOnClickListener{
            val intent = Intent(this, PemasukanActivity::class.java)
            startActivity(intent)
        }

        btn_pengeluaran.setOnClickListener{
            val intent = Intent(this, PengeluaranActivity::class.java)
            startActivity(intent)
        }

    }

}