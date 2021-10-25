package com.example.notes_app_w6_d1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBase( context: Context?) : SQLiteOpenHelper(context, "details.db", null, 1) {
    val sqLiteDatabase = writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("Create table students (Name text, Location text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun saveData(s1: String, s2: String){
        val cv = ContentValues()
        cv.put("Name", s1)
        cv.put("Location",s2)
        sqLiteDatabase.insert("students", null, cv)
    }
}