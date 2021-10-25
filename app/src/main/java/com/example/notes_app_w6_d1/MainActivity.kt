package com.example.notes_app_w6_d1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes_app_w6_d1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbHelper = DataBase(this)
        initializeBinding()
        initializeRecycler()
        binding.button.setOnClickListener{
            dbHelper.saveData(binding.etName.text.toString(), binding.etLoc.text.toString())
            Toast.makeText(this,"data saved!!", Toast.LENGTH_LONG)
        }
    }

    private lateinit var binding: ActivityMainBinding
    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private lateinit var adapter: Recycler
    private lateinit var messages: ArrayList<String>
    private fun initializeRecycler() {
        messages = ArrayList()
        adapter = Recycler(messages)
        binding.rvMain.adapter = adapter
        binding.rvMain.layoutManager = LinearLayoutManager(this)
    }
}

