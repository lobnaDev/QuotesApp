package com.app.quotesapp.api

import android.content.Context
import android.location.GnssAntennaInfo
import com.app.quotesapp.model.QuotesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Lobna Babker on 10/6/2022.
 */
class RequestManger(mContext: Context) {

    var retrofit =  Retrofit.Builder()
            .baseUrl("https://type.fit/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun GetAllQuotes(Listener:QuotesResponseListener) {
        val call = retrofit.create(CallQuotes::class.java).callQuotes()
        call.enqueue(object : Callback<List<QuotesResponse>>{
            override fun onResponse(
                call: Call<List<QuotesResponse>>,
                response: Response<List<QuotesResponse>>
            ) {
                if(!response.isSuccessful){
                    Listener.didError(response.message())
                    return
                }
                response.body()?.let { Listener.didFetch(it,response.message()) }
            }

            override fun onFailure(call: Call<List<QuotesResponse>>, t: Throwable) {
                t.message?.let { Listener.didError(it)
                }
            }

        })
    }

    private interface CallQuotes{
        @GET("api/quotes")
        fun callQuotes(): Call<List<QuotesResponse>>
    }
}