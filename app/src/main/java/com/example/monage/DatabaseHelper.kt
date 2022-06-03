package com.example.monage

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private val DATABASE_NAME = "monage.db"
        private val DATABASE_VERSION = 1

        private val TABLE_NAME = "monage_database"
        private val COLUMN_ID = "_id"
        private val COLUMN_SALDO = "saldo"
        private val COLUMN_AKSI = "aksi"
        private val COLUMN_KATEGORI = "kategori"
        private val COLUMN_TANGGAL = "tanggal"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + "INTEGER PRIMARY KEY,"
                + COLUMN_SALDO + " INTEGER,"
                + COLUMN_AKSI + " INTEGER,"
                + COLUMN_KATEGORI + " TEXT,"
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
}