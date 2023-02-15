package com.example.xarajatlarhisobi.Dao

import androidx.room.*
import com.example.xarajatlarhisobi.Models.Registr

@Dao
interface RegistrDao {

    @Query("select * from registr")
    fun getAllRegistr(): List<Registr>

    @Insert
    fun addRegistr(report: Registr)


}