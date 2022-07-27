package com.example.applicationwithmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applicationwithmvvm.network.SharedPreferencesRepository

class MainViewModel(private val pref: SharedPreferencesRepository) : ViewModel() {

    val name = MutableLiveData<String>()
    private val isNameCorrect = MutableLiveData(false)

    val year = MutableLiveData<String?>()
    private val isYearCorrect = MutableLiveData(false)

    val times = MutableLiveData<String?>()
    private val isTimesCorrect = MutableLiveData(false)

    private val _result = MutableLiveData("")
    val result: LiveData<String> = _result

    val isVisibleDialog = MutableLiveData<Boolean>()
    val isSuccessfulSavedData = MutableLiveData<Boolean>()
    val isEnableButton = MutableLiveData(false)

    init {
        name.observeForever {
            if (it.isNullOrBlank() || it.length <= 3) {
                settingsForEditText(
                    "Field `name` cannot be empty and more than three characters!",
                    isNameCorrect
                )
            } else {
                isNameCorrect.value = true
                _result.value = ""
            }

            checkFields()
        }

        year.observeForever {
            if (it.isNullOrBlank()) {
                settingsForEditText("Field `year` cannot be empty !", isYearCorrect)
            } else if (it.toInt() < 1000 || it.toInt() > 2000) {
                settingsForEditText("Field `year` must be from 1000 to 2000!", isYearCorrect)
            } else {
                isYearCorrect.value = true
                _result.value = ""
            }

            checkFields()

        }

        times.observeForever {
            if (it.isNullOrBlank()) {
                settingsForEditText("Field `times` cannot be empty!", isTimesCorrect)
            } else {
                isTimesCorrect.value = true
                _result.value = ""
            }
            checkFields()
        }
    }

    fun save() {
        saveInfo("name = ${name.value}; year = ${year.value}; times = ${times.value}")
        _result.value = "Successfully"
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
        _result.value = pref.info
        isSuccessfulSavedData.value = true
    }

    private fun saveInfo(infoLikeStr: String?) {
        pref.info = infoLikeStr
    }

    private fun settingsForEditText(value: String, liveData: MutableLiveData<Boolean>) {
        _result.value = value
        isSuccessfulSavedData.value = false
        liveData.value = false
        isEnableButton.value = false
    }

    private fun checkFields() {
        if (isNameCorrect.value == true && isYearCorrect.value == true && isTimesCorrect.value == true) {
            isEnableButton.value = true
            _result.value = ""
        }
    }

}