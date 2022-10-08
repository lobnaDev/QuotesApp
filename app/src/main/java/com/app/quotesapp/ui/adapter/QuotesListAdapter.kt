package com.app.quotesapp.ui.adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.quotesapp.R
import com.app.quotesapp.model.QuotesResponse

/**
 * Created by Lobna Babker on 10/6/2022.
 */
class QuotesListAdapter(val context: Context, val list :List<QuotesResponse>,val listener : QuotesViewHolder.CopyListener) :
    RecyclerView.Adapter<QuotesListAdapter.QuotesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.list_quotes,parent,false)
        return QuotesViewHolder(layout)
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        holder.textView_quotes.text = list.get(position).text
        holder.textView_auother.text = list.get(position).author
        holder.button_copy.setOnClickListener{
            listener.onCopyClick(list.get(holder.adapterPosition).text)
        }
    }

    override fun getItemCount(): Int {
     return list.size
    }

    class QuotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView_quotes : TextView = itemView.findViewById(R.id.textview_quotes)
        var textView_auother : TextView = itemView.findViewById(R.id.textview_author)
        var button_copy : Button = itemView.findViewById(R.id.button_copy)


        interface CopyListener{
            fun onCopyClick(text:String)
        }
    }
}
