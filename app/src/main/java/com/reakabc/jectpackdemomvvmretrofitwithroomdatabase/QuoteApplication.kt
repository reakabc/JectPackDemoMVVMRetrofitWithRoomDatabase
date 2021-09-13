package com.reakabc.jectpackdemomvvmretrofitwithroomdatabase

import android.app.Application
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.api.QuoteService
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.api.RetrofitHelper
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.db.QuoteDatabase
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.repository.QuoteRepository

class QuoteApplication: Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize();
    }

    private fun initialize() {

        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getInstance(applicationContext)
        quoteRepository = QuoteRepository(quoteService, database, applicationContext)

    }

}