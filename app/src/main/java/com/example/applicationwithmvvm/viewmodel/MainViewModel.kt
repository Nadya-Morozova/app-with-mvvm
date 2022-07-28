package com.example.applicationwithmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applicationwithmvvm.network.SharedPreferencesRepository

class MainViewModel(private val pref: SharedPreferencesRepository) : ViewModel() {

    val name = MutableLiveData<String>()
    val year = MutableLiveData("")
    val times = MutableLiveData("")

//    private val _result = MutableLiveData("")
//    val result: LiveData<String> = _result

    private val _result = MutableLiveData<List<String>?>()
    val result: LiveData<List<String>?> = _result

    private val listOfWords = mutableListOf<String>()

    val isVisibleDialog = MutableLiveData<Boolean>()
    val isSuccessfulSavedData = MutableLiveData<Boolean>()

    fun save() {
        if (name.value.isNullOrBlank()) {
            setTextAndTextColor("Field `name` cannot be empty or null!")
            return
        }

        if (year.value.isNullOrBlank()) {
            setTextAndTextColor("Field `year` cannot be empty or null!")
            return
        }

        if ((year.value?.toInt() ?: 0) < 1000 || (year.value?.toInt() ?: 0) > 2000) {
            setTextAndTextColor("Field `year` must be from 1000 to 2000!")
            return
        }

        if (times.value.isNullOrBlank()) {
            setTextAndTextColor("Field `times` cannot be empty or null!")
            return
        }

        saveInfo("name = ${name.value}; year = ${year.value}; times = ${times.value}")
        listOfWords.add("Successfully")
        _result.value = listOfWords
        isSuccessfulSavedData.value = true
        isVisibleDialog.value = true
    }

    fun swipe() {
        val valueYear = year.value
        val valueTimes = times.value

        year.value = valueTimes
        times.value = valueYear

    }

    fun loadInfo() {
        listOfWords.add(pref.info ?: "")
        _result.value = listOfWords
        isSuccessfulSavedData.value = true
    }

    private fun saveInfo(infoLikeStr: String?) {
        pref.info = infoLikeStr
    }

    private fun setTextAndTextColor(value: String) {
        listOfWords.add(value)
        _result.value = listOfWords
        isSuccessfulSavedData.value = false
    }

}