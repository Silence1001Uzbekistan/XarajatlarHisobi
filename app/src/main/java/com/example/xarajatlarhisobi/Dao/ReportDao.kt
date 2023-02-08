package com.example.xarajatlarhisobi.Dao

import androidx.room.*
import com.example.xarajatlarhisobi.Models.Report

@Dao
interface ReportDao {

    @Query("select * from report")
    fun getAllReport(): List<Report>

    @Insert
    fun addReport(report: Report)

    @Delete
    fun deleteReport(report: Report)

    @Update
    fun updateReport(report: Report)

    @Query("select * from report where id=:id")
    fun getReportById(id: Int): Report

    @Query("select * from report where username=:username")
    fun getReportByList(username: String): List<Report>

    @Query("select id from report where objectName=:productNameN")
    fun getCitizenById(productNameN: String): Int

}