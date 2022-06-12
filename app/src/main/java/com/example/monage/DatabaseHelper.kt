package com.example.monage

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import java.lang.Exception

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private val DATABASE_NAME = "monage.dasdb"
        private val DATABASE_VERSION = 1

        private val TABLE_NAME = "monage_database"
        private val COLUMN_ID = "_id"
        private val COLUMN_SALDO = "saldo"
        private val COLUMN_AKSI = "aksi"
        private val COLUMN_KATEGORI = "kategori"
        private val COLUMN_TANGGAL = "tanggal"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_SALDO + " INTEGER, "
                + COLUMN_AKSI + " INTEGER, "
                + COLUMN_KATEGORI + " TEXT, "
                + COLUMN_TANGGAL + " TEXT" + ")")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, v1: Int, v2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addSaldo(sal: SaldoModelClass): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(COLUMN_SALDO, sal.saldo)
        contentValues.put(COLUMN_AKSI, sal.aksi)
        contentValues.put(COLUMN_KATEGORI, sal.kategori)
        contentValues.put(COLUMN_TANGGAL, sal.tanggal)

        val success = db.insert(TABLE_NAME, null, contentValues)

        db.close()
        return success
    }
    fun getAllSaldo(mCtx:Context): ArrayList<SaldoModelClass2> {
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        val salList = ArrayList<SaldoModelClass2>()
        if (cursor.count == 0)
            Toast.makeText(mCtx, "Data tidak ditemukan", Toast.LENGTH_SHORT).show() else {
            while (cursor.moveToNext()) {
                val saldo = SaldoModelClass2()
                saldo.id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                saldo.saldo = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SALDO))
                saldo.tanggal = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TANGGAL))
                saldo.kategori = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KATEGORI))
                saldo.aksi = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AKSI))
                salList.add(saldo)
            }
            Toast.makeText(mCtx,"${cursor.count.toString()} Data ditemukan", Toast.LENGTH_SHORT).show()
        }
        cursor.close()
        db.close()
        return salList
    }
    fun deleteSaldo(id: Int) : Boolean{
        val qry = "DELETE FROM $TABLE_NAME where $COLUMN_ID = $id"
        val db = this.writableDatabase
        var result : Boolean = false
        try{
            val cursor = db.execSQL(qry)
            result = true
        }catch (e:Exception){
            Log.e(ContentValues.TAG, "Hapus Eror")
        }
        db.close()
        return result
    }
    fun editSaldo(id: String, kategori: String, tanggal: String, saldo: Int) : Boolean{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        var result : Boolean = false
        contentValues.put(COLUMN_KATEGORI,kategori)
        contentValues.put(COLUMN_TANGGAL,tanggal)
        contentValues.put(COLUMN_SALDO,saldo)
        try{
            db.update(TABLE_NAME,contentValues,"$COLUMN_ID = ?", arrayOf(id))
            result = true
        }catch (e:Exception){
            Log.e(ContentValues.TAG, "Update Eror")
            result = false
        }
        return result
    }

}

