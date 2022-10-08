package com.app.quotesapp.ui

import android.app.ProgressDialog
import android.content.ClipData
import android.content.ClipboardManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app.quotesapp.R
import com.app.quotesapp.api.QuotesResponseListener
import com.app.quotesapp.api.RequestManger
import com.app.quotesapp.model.QuotesResponse
import com.app.quotesapp.ui.adapter.QuotesListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , QuotesListAdapter.QuotesViewHolder.CopyListener{
    var dialog:ProgressDialog?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RequestManger(this@MainActivity).GetAllQuotes(Listener)
        dialog = ProgressDialog(this@MainActivity)
        dialog?.setTitle("Loading...")
        dialog?.show()
    }
    private val Listener :QuotesResponseListener = object:QuotesResponseListener{
        override fun didFetch(response: List<QuotesResponse>, message: String) {
          dialog?.dismiss()
            recyclerView_home.setHasFixedSize(true)
            recyclerView_home.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
            val adapter = QuotesListAdapter(this@MainActivity,response,this@MainActivity)
            recyclerView_home.adapter =adapter
        }

        override fun didError(message: String) {
            dialog?.dismiss()
            Toast.makeText(this@MainActivity,message,Toast.LENGTH_LONG).show()
        }

    }


    override fun onCopyClick(text: String) {
        val clipboard = getSystemService(CLIPBOARD_SERVICE)as ClipboardManager
        val clip = ClipData.newPlainText("Copied_Data",text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this@MainActivity,"Quote Copied to Clipboard!",Toast.LENGTH_LONG).show()
    }
}