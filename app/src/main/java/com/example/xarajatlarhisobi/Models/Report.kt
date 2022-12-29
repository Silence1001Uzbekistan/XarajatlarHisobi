package com.example.xarajatlarhisobi.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Report {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var objectName: String? = null
    var productType: Int? = null
    var produvtName: String? = null
    var productPrice: String? = null
    var productCommet: String? = null
    var productImage:Int? = null


}