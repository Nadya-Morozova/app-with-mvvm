package com.example.applicationwithmvvm.views.adapters

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("changeColor")
fun changeColor(view: TextView, value: Boolean?) {
    if (value == true) view.setTextColor(Color.GREEN)
    else view.setTextColor(Color.RED)
}
