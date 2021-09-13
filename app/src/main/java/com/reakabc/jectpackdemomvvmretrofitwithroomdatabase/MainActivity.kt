package com.reakabc.jectpackdemomvvmretrofitwithroomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.api.QuoteService
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.api.RetrofitHelper
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.repository.QuoteRepository
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.repository.Response
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.viewmodels.MainViewModel
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = (application as QuoteApplication).quoteRepository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, {
            when(it){
                is Response.Loading -> {}
                is Response.Error -> {
                    it.data?.let{
                        Toast.makeText(this, it.results.size, Toast.LENGTH_SHORT).show()
                    }
                }
                is Response.Success -> {
                    it.errorMessage
                    Toast.makeText(this, "Error occured!", Toast.LENGTH_SHORT).show()
                }
            }

        })

    }



}