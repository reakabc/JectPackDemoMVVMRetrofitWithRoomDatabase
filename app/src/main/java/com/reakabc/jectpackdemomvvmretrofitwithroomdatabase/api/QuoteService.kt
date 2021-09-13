package com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.api

import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.models.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {

    //http://quotable.io/

    @GET("quotes")
    suspend fun getQuote(@Query("page") page:Int) : Response<QuoteList>

}