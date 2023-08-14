package com.example.track_budget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TransactionAdapter(private var transactions: List<Transaction>): RecyclerView.Adapter<TransactionAdapter.TransactionHolder>() {

    class TransactionHolder(view: View): RecyclerView.ViewHolder(view){
        val Label: TextView = view.findViewById(R.id.Labeltxt)
        val Amount: TextView = view.findViewById(R.id.Amounttxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaction_layout, parent, false)
        return TransactionHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        val transaction = transactions[position]
        val context = holder.Amount.context

        if(transaction.amount >= 0)
        {
            holder.Amount.text = "+ $%.2f".format(transaction.amount)
            holder.Amount.setTextColor(ContextCompat.getColor(context, R.color.green))
        }else{
            holder.Amount.text = "- $%.2f".format(transaction.amount)
            holder.Amount.setTextColor(ContextCompat.getColor(context, R.color.red))
        }

        holder.Label.text = transaction.label
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    fun setData(transactions: List<Transaction>)
    {
        this.transactions = transactions
        notifyDataSetChanged()
    }

}