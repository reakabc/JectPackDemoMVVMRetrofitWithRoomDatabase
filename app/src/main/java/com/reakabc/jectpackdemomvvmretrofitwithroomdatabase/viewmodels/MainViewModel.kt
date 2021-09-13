package com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.models.QuoteList
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.repository.QuoteRepository
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepository): ViewModel() {

    init {

        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotes(1)
        }

    }

    val quotes : LiveData<Response<QuoteList>>
    get() = repository.quotes

}