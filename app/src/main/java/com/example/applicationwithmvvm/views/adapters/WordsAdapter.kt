package com.example.applicationwithmvvm.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationwithmvvm.databinding.ListItemBinding

class WordsAdapter(private var list: List<String>) : RecyclerView.Adapter<WordsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder =
        WordsViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    fun update(listOfString: List<String>) {
        list = listOfString
        notifyDataSetChanged()
    }

}

class WordsViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(word: String) {
        binding.tvWord.text = word
    }

}