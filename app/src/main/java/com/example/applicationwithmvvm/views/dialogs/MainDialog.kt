package com.example.applicationwithmvvm.views.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MainDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alert = AlertDialog.Builder(context)
        alert.apply {
            setTitle("Just dialog")
            setMessage("Data saved successfully!")
            setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }
        }
        return alert.create()
    }

}