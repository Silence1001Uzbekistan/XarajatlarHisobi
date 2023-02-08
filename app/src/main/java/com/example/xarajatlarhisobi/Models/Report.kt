package com.example.xarajatlarhisobi.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Report:Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var username: String? = null
    var password: String? = null
    var objectName: String? = null
    var productType: Int? = null
    var produvtName: String? = null
    var productPrice: String? = null
    var productCommet: String? = null
    var productImage: String? = null

    var productDate: String? = null


}