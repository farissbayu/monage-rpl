package com.example.monage

class SaldoModelClass(
    val id: Int,
    val saldo: Int,
    val aksi: Int,
    val kategori: String,
    val tanggal: String
)
class SaldoModelClass2{
    var id: Int = 0
    var saldo: Int = 0
    var aksi: Int = 0
    var kategori: String = ""
    var tanggal: String = ""
    var total: Int = 0
    var totPem: Int = 0
    var totPen: Int = 0
}