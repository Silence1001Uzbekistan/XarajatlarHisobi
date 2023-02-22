package com.example.xarajatlarhisobi.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class ObjectMinus: Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var objectBigName: String? = null
    var username: String? = null
    var password: String? = null
    var giveName: String? = null
    var cash:String? = null
    var dateName:String? = null

}