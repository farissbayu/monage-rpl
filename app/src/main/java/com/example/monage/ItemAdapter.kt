package com.example.monage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rview_item_pemasukan.view.*


class ItemAdapter(val context: Context, val items: ArrayList<SaldoModelClass>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.rview_item_pemasukan,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items.get(position)

        holder.tv_kategori_pemasukan.text = item.kategori
        holder.tv_tgl_pemasukan.text = item.tanggal
        holder.tv_nmnl_pemasukan.text = item.saldo.toString()


    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each item to
        val tv_nmnl_pemasukan = view.tv_nmnl_pemasukan
        val tv_tgl_pemasukan = view.tv_tgl_pemasukan
        val tv_kategori_pemasukan = view.tv_kategori_pemasukan

    }


}