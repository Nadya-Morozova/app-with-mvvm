package com.example.applicationwithmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applicationwithmvvm.databinding.ActivityMainBinding
import com.example.applicationwithmvvm.network.SharedPreferencesRepository
import com.example.applicationwithmvvm.viewmodel.MainViewModel
import com.example.applicationwithmvvm.viewmodel.MainViewModelFactory
import com.example.applicationwithmvvm.views.adapters.WordsAdapter
import com.example.applicationwithmvvm.views.dialogs.MainDialog

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var adapter = WordsAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(SharedPreferencesRepository(applicationContext))
        )[MainViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initDialogListener()
        initRecyclerView()
    }

    private fun initDialogListener() {
        viewModel.isVisibleDialog.observe(this) {
            if (it == true) {
                MainDialog().show(supportFragmentManager, "")
            }
        }
    }

    private fun initRecyclerView() {
        viewModel.result.observe(this) {
            adapter.update(it ?: mutableListOf())
            binding.apply {
                rvWords.adapter = adapter
                rvWords.layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, true)
            }

        }
    }

}