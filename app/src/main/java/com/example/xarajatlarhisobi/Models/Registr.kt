package com.example.xarajatlarhisobi.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Registr:Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var username: String? = null
    var password: String? = null


}