package com.example.xarajatlarhisobi.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.xarajatlarhisobi.Dao.ReportDao
import com.example.xarajatlarhisobi.Models.Report

@Database(entities = [Report::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun citizenDao(): ReportDao


    companion object {

        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {

            if (instance == null) {

                instance = Room.databaseBuilder(context, AppDatabase::class.java, "citizens_db")
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build()

            }

            return instance!!

        }

    }

}