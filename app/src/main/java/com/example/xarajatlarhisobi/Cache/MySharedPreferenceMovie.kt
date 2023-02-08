package com.example.movieappviewbindingandcache.Cache

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

object MySharedPreferenceMovie {

    private const val NAME = "registration"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context){
        sharedPreferences = context.getSharedPreferences(NAME, MODE)
    }

    @SuppressLint("CommitPrefEdits")
    private inline fun SharedPreferences.edit(operation:(SharedPreferences.Editor) -> Unit){
        val editor = edit()
        operation(editor)
        editor.apply()
    }


    var user:String?
        get() =  sharedPreferences.getString("user","{}")
        set(value) = sharedPreferences.edit {
            if (value != null){
                it.putString("user",value)
            }
        }

    var pass:String?
        get() =  sharedPreferences.getString("pass","{}")
        set(value) = sharedPreferences.edit {
            if (value != null){
                it.putString("pass",value)
            }
        }

/*
    var listSave: ArrayList<MovieClass>
        get() = gsonStringToArray(sharedPreferences.getString("obekt", "[]")!!)
        set(value) = sharedPreferences.edit {
            if (value != null) {
                it.putString("obekt", arrayToGsonString(value))
            }
        }

    private fun gsonStringToArray(gsonString: String): ArrayList<MovieClass> {
        val type = object : TypeToken<ArrayList<MovieClass>>() {}.type
        return Gson().fromJson(gsonString, type)
    }

    fun arrayToGsonString(arrayList: ArrayList<MovieClass>): String? {
        return Gson().toJson(arrayList)
    }
*/

}