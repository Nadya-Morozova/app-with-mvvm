package com.example.applicationwithmvvm.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationwithmvvm.data.Word
import com.example.applicationwithmvvm.databinding.ListItemBinding

class WordsAdapter(private var list: List<Word>) : RecyclerView.Adapter<WordsViewHolder>() {

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

    fun updateList(list: List<Word>) {
        this.list = list
        notifyItemInserted(0)
        notifyItemChanged(0)
    }

}

class WordsViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(word: Word) {
        binding.word = word.name
        binding.color = word.color
    }

}