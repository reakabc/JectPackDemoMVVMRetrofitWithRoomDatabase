package com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.api.QuoteService
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.db.QuoteDatabase
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.models.QuoteList
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.repository.Response
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.utils.NetworkUtils
import java.lang.Exception

class QuoteRepository(
    private val quoteService: QuoteService,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val quotesLiveData = MutableLiveData<Response<QuoteList>>()

    val quotes: MutableLiveData<Response<QuoteList>>
    get() = quotesLiveData

    suspend fun getQuotes(page: Int){

        if (NetworkUtils.isOnline(applicationContext)){

            try {
                val result = quoteService.getQuote(page);
                if (result?.body() != null){

                    quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                    /*quotesLiveData.postValue(result.body())*/
                    quotesLiveData.postValue(Response.Success(result.body()))

                }
            }catch (e: Exception){
                quotesLiveData.postValue(Response.Error(e.message.toString()))
            }

        }else{

            val quotes = quoteDatabase.quoteDao().getQuotes();
            val quoteList = QuoteList(1, 1, 1, quotes, 1, 2)
            quotesLiveData.postValue(Response.Success(quoteList))

        }



    }

}