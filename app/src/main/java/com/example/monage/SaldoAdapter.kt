package com.example.monage

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.rview_item_pemasukan.view.*

class SaldoAdapter (mCtx : Context, val salList : ArrayList<SaldoModelClass2>) : RecyclerView.Adapter<SaldoAdapter.ViewHolder>() {
    val mCtx = mCtx

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtid = itemView.tv_id
        val txtaksi = itemView.tv_aksi
        val txtkategori = itemView.tv_kategori_pemasukan
        val txtsaldo = itemView.tv_nmnl_pemasukan
        val txttgl = itemView.tv_tgl_pemasukan
        val lay = itemView.lySaldo
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SaldoAdapter.ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.rview_item_pemasukan,p0,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(p0: SaldoAdapter.ViewHolder, p1: Int) {
        val saldo : SaldoModelClass2 = salList[p1]
        p0.txtid.text = saldo.id.toString()
        p0.txtaksi.text = saldo.aksi.toString()
        p0.txtkategori.text = saldo.kategori
        p0.txtsaldo.text = saldo.saldo.toString()
        p0.txttgl.text = saldo.tanggal

        if(p0.txtaksi.text=="1"){
            p0.lay.setBackgroundResource(R.drawable.bg_item_rv_pemasukan)
        }
        else{
            p0.lay.setBackgroundResource(R.drawable.bg_item_rv_pengeluaran)
            p0.txtkategori.setBackgroundResource(R.drawable.kategori_bg_pengeluaran)
        }

        p0.itemView.setOnClickListener {

            var alertDialog = AlertDialog.Builder(mCtx)
                .setTitle("Pilih Aksi")
                .setPositiveButton("Hapus", DialogInterface.OnClickListener { dialog, which ->
                    val databaseHandler: DatabaseHelper = DatabaseHelper(mCtx)
                      if (databaseHandler.deleteSaldo(saldo.id)){
                          salList.removeAt(p1)
                          notifyItemRemoved(p1)
                          notifyItemRangeChanged(p1,salList.size)
                          Toast.makeText(mCtx,"Hapus Sukses",Toast.LENGTH_SHORT).show()
                      }else
                          Toast.makeText(mCtx,"Hapus Error",Toast.LENGTH_SHORT).show()
                })
                .setNegativeButton("Edit", DialogInterface.OnClickListener { dialog, which ->
                    val inflater = LayoutInflater.from(mCtx)
                    val view = inflater.inflate(R.layout.activity_edit_pemasukan,null)
                    val txKat : TextView = view.findViewById(R.id.txKat)
                    val txTgl : TextView = view.findViewById(R.id.pilih_tanggal_pemasukan)
                    val txNml : TextView = view.findViewById(R.id.txNom)
                    val txAksi : TextView = view.findViewById(R.id.txAksi)
                    val txId : TextView = view.findViewById(R.id.idKat)

                    txId.text = saldo.id.toString()
                    txAksi.text = saldo.aksi.toString()
                    txKat.text = saldo.kategori
                    txTgl.text = saldo.tanggal
                    txNml.text = saldo.saldo.toString()
                    if(txAksi.text=="1") {
                        val kirim = Intent(mCtx, EditPemasukanActivity::class.java)
                        kirim.putExtra("xId", txId.text)
                        kirim.putExtra("xKat", txKat.text)
                        kirim.putExtra("xTgl", txTgl.text)
                        kirim.putExtra("xNml", txNml.text)
                        mCtx.startActivity(kirim)
                    }
                    else{
                        val kirim = Intent(mCtx, EditPengeluaranActivity::class.java)
                        kirim.putExtra("xId", txId.text)
                        kirim.putExtra("xKat", txKat.text)
                        kirim.putExtra("xTgl", txTgl.text)
                        kirim.putExtra("xNml", txNml.text)
                        mCtx.startActivity(kirim)
                    }
                })
                .show()
        }

    }

    override fun getItemCount(): Int {
        return salList.size
    }

}