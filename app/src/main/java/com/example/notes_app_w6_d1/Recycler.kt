package com.example.notes_app_w6_d1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_app_w6_d1.databinding.RecyclerLayoutBinding

class Recycler(): RecyclerView.Adapter<Recycler.ViewHolder>() {
    private var messages: ArrayList<Note> = ArrayList()
    class ViewHolder(val binding: RecyclerLayoutBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var message = messages[position]
        holder.binding.apply {
            tvID.text = message.id.toString()
            tvNote.text = message.text
        }
    }

    override fun getItemCount() = messages.size

    fun update(newMessages: ArrayList<Note>){
        this.messages = newMessages
        notifyDataSetChanged()
    }
}