package com.example.applicationwithmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.applicationwithmvvm.databinding.ActivityMainBinding
import com.example.applicationwithmvvm.network.SharedPreferencesRepository
import com.example.applicationwithmvvm.viewmodel.MainViewModel
import com.example.applicationwithmvvm.viewmodel.MainViewModelFactory
import com.example.applicationwithmvvm.views.dialogs.MainDialog

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(SharedPreferencesRepository(applicationContext))
        )[MainViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        if (savedInstanceState == null) {
            initDialogListener()
        }

    }

    private fun initDialogListener() {
        viewModel.isVisibleDialog.observe(this) {
            if (it == true) {
                MainDialog().show(supportFragmentManager, "")
            }
        }
    }

}