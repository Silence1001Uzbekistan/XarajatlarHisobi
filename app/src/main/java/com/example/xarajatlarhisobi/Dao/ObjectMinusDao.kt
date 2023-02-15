package com.example.xarajatlarhisobi.Dao

import androidx.room.*
import com.example.xarajatlarhisobi.Models.ObjectMinus

@Dao
interface ObjectMinusDao {

    @Query("select * from objectminus")
    fun getAllObjectMinus(): List<ObjectMinus>

    @Insert
    fun addObjectMinus(objectMinus: ObjectMinus)

    @Query("select * from objectminus where username =:userName  AND password=:passName")
    fun getObjectByList(userName: String, passName: String): List<ObjectMinus>

    @Query("select * from objectminus where username =:userName  AND password=:passName AND objectBigName=:objectName")
    fun getObjectNameByList(userName: String, passName: String, objectName: String): List<ObjectMinus>

}