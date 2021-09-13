package com.reakabc.jectpackdemomvvmretrofitwithroomdatabase

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.api.QuoteService
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.api.RetrofitHelper
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.db.QuoteDatabase
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.repository.QuoteRepository
import com.reakabc.jectpackdemomvvmretrofitwithroomdatabase.worker.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication: Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
        setUpWorker()
    }

    private fun initialize() {

        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getInstance(applicationContext)
        quoteRepository = QuoteRepository(quoteService, database, applicationContext)

    }

    private fun setUpWorker(){

        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest = PeriodicWorkRequest.Builder(QuoteWorker::class.java, 30, TimeUnit.MINUTES)
            .setConstraints(constraint).build()

        WorkManager.getInstance(this).enqueue(workerRequest)

    }

}