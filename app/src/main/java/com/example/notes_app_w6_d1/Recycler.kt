package com.example.notes_app_w6_d1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_app_w6_d1.databinding.RecyclerLayoutBinding

class Recycler(val messages: ArrayList<String>): RecyclerView.Adapter<Recycler.ViewHolder>() {
    class ViewHolder(val binding: RecyclerLayoutBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = messages[position]
        holder.binding.apply {
            //tvRecyler.text = message
        }
    }

    override fun getItemCount() = messages.size
}