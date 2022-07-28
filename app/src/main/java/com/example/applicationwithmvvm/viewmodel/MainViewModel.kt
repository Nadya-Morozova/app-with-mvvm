package com.example.applicationwithmvvm.viewmodel

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applicationwithmvvm.data.Word
import com.example.applicationwithmvvm.network.SharedPreferencesRepository

class MainViewModel(private val pref: SharedPreferencesRepository) : ViewModel() {

    private val listOfWords = mutableListOf<Word>()

    val name = MutableLiveData<String>()
    val year = MutableLiveData<String?>()
    val times = MutableLiveData<String?>()

    private val _result = MutableLiveData<List<Word>?>()
    val result: LiveData<List<Word>?> = _result

    val isVisibleDialog = MutableLiveData<Boolean>()

    fun save() {
        if (name.value.isNullOrBlank()) {
            setTextAndTextColor("Field `name` cannot be empty!", Color.RED)
            return
        }

        if (year.value.isNullOrBlank()) {
            setTextAndTextColor("Field `year` cannot be empty!", Color.RED)
            return
        }

        if ((year.value?.toInt() ?: 0) < 1000 || (year.value?.toInt() ?: 0) > 2000) {
            setTextAndTextColor("Field `year` must be from 1000 to 2000!", Color.RED)
            return
        }

        if (times.value.isNullOrBlank()) {
            setTextAndTextColor("Field `times` cannot be empty!", Color.RED)
            return
        }

        saveInfo("name = ${name.value}; year = ${year.value}; times = ${times.value}")
        isVisibleDialog.value = true
        setTextAndTextColor("Successfully", Color.GREEN)
    }

    fun swipe() {
        val valueYear = year.value
        val valueTimes = times.value

        year.value = valueTimes
        times.value = valueYear

    }

    fun loadInfo() {
        listOfWords.add(0, Word(pref.info ?: "", Color.GREEN))
        _result.value = listOfWords
    }

    private fun saveInfo(infoLikeStr: String?) {
        pref.info = infoLikeStr
    }

    private fun setTextAndTextColor(value: String, color: Int) {
        listOfWords.add(0, Word(value, color))
        _result.value = listOfWords
    }

}