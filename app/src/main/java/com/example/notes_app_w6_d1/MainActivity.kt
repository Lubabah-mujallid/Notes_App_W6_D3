package com.example.notes_app_w6_d1

import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes_app_w6_d1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = DataBase(this)
        initializeBinding()
        initializeRecycler()
    }


    fun submitButton(view: View) {
        Log.d("MAIN", "Button Pressed")
        addNote()
        updateRV()
    }

    private fun addNote(){
        //save data to Database
        dbHelper.saveData(binding.etNote.text.toString())
        binding.etNote.text.clear()
        Log.d("MAIN", "new data added")
        Toast.makeText(this,"data saved!!", Toast.LENGTH_LONG)
    }

    private lateinit var messages: ArrayList<Note>
    private fun updateRV(){
        //update rv with all data + new data
        messages = dbHelper.retrieveData()
        Log.d("MAIN", "messages retrived from database: " + messages.toString())
        adapter.update(messages)
        Log.d("MAIN", "RV updated")
        Log.d("MAIN", "RV item count" + adapter.itemCount)
    }

    private lateinit var binding: ActivityMainBinding
    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("MAIN", "Binding")
    }

    private lateinit var adapter: Recycler
    private fun initializeRecycler() {
        adapter = Recycler()
        binding.rvMain.adapter = adapter
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        Log.d("MAIN", "RV")
        updateRV()
    }


}

