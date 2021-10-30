package com.example.notes_app_w6_d1

import android.app.AlertDialog
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes_app_w6_d1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var dbHelper: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = DataBase(this)
        initializeBinding()
        initializeRecycler()
    }

    fun submitButton(view: View) {
        Log.d("TAG MAIN", "Button Pressed")
        addNote()
        updateRV()
    }

    private fun addNote() {
        //save data to Database
        dbHelper!!.saveData(binding.etNote.text.toString())
        binding.etNote.text.clear()
        Log.d("TAG MAIN", "new data added")
        Toast.makeText(this, "data saved!!", Toast.LENGTH_LONG)
    }

    private fun updateNote(note: Note, nNote: String) {
        Log.d("TAG MAIN", "INSIDE UPDATE")
        //Log.d("MAIN", "before "+dbHelper.toString())
        //dbHelper = DataBase(this)
        Log.d("TAG MAIN", "after $dbHelper")
        dbHelper.updateData(note, nNote)
        updateRV()
    }

    private fun deleteNote(note: Note){
        Log.d("TAG MAIN", "INSIDE delete")
        dbHelper.deleteData(note)
        updateRV()
    }

    private lateinit var messages: ArrayList<Note>
    private fun updateRV() {
        //update rv with all data + new data
        messages = dbHelper.retrieveData()
        Log.d("TAG MAIN", "messages retrived from database: \n" + messages.toString())
        adapter.update(messages)
        Log.d("TAG MAIN", "RV updated")
    }

    private lateinit var binding: ActivityMainBinding
    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("TAG MAIN", "Binding")
    }

    private lateinit var adapter: Recycler
    private fun initializeRecycler() {
        adapter = Recycler(this)
        binding.rvMain.adapter = adapter
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        Log.d("TAG MAIN", "RV")
        updateRV()
    }

    fun alertDialog(isUpdate: Boolean, note: Note) {
        val dialogBuilder = AlertDialog.Builder(this)
        val newLayout = LinearLayout(this)
        newLayout.orientation = LinearLayout.VERTICAL
        if (isUpdate) {
            val newTask = EditText(this)
            newTask.hint = "update note: "
            newLayout.addView(newTask)

            Log.d("TAG ALERT", "INSIDE UPDATE")

            dialogBuilder
                .setPositiveButton("Update") { dialog, id ->
                    Log.d("TAG ALERT", "INSIDE POS BUTTON")
                    Log.d("TAG ALERT", "NOTE IS: $note")
                    updateNote(note, newTask.text.toString())
                    //adapter.notifyDataSetChanged()
                }
                .setNegativeButton("Cancel") { dialog, id -> dialog.cancel() }

        } // update note
        else {
            val text = TextView(this)
            text.text = "Are you sure you wish to delete note? "
            newLayout.addView(text)

            //pos > delete
            //neg > cancel
            dialogBuilder
                .setPositiveButton("Delete") { dialog, id ->
                    Log.d("TAG ALERT", "INSIDE POS BUTTON")
                    Log.d("TAG ALERT", "NOTE IS: $note")
                    deleteNote(note)
                }
                .setNegativeButton("Cancel") { dialog, id -> dialog.cancel() }

        } //delete note
        val alert = dialogBuilder.create()
        alert.setTitle("Note")
        alert.setView(newLayout)
        alert.show()
    }
}

