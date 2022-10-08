package com.app.quotesapp.api

import com.app.quotesapp.model.QuotesResponse

/**
 * Created by Lobna Babker on 10/6/2022.
 */
interface QuotesResponseListener {
    fun didFetch(response:List<QuotesResponse>,message:String)

    fun didError(message: String)
}