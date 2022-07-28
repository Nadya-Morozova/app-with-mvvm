package com.example.applicationwithmvvm.views.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationwithmvvm.data.Word

@BindingAdapter("observeList")
fun RecyclerView.observeList(list: List<Word>?) {
    val adapter = WordsAdapter(mutableListOf())

    adapter.updateList(list ?: listOf())
    this.adapter = adapter
    this.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
}