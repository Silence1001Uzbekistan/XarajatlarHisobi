package com.example.xarajatlarhisobi.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.xarajatlarhisobi.Dao.RegistrDao
import com.example.xarajatlarhisobi.Models.Registr

@Database(entities = [Registr::class], version = 1)
abstract class AppDatabaseR : RoomDatabase() {

    abstract fun registrDao(): RegistrDao


    companion object {

        private var instance: AppDatabaseR? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabaseR {

            if (instance == null) {

                instance = Room.databaseBuilder(context, AppDatabaseR::class.java, "as_db")
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build()

            }

            return instance!!

        }

    }

}