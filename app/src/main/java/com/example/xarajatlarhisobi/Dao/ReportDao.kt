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

}