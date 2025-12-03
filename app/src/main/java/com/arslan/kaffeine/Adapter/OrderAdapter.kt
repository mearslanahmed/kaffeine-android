package com.arslan.kaffeine.Adapter

import android.content.Context
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arslan.kaffeine.Domain.Order
import com.arslan.kaffeine.databinding.ViewholderOrderBinding
import java.util.concurrent.TimeUnit

class OrderAdapter(private val orders: List<Order>, private val context: Context) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderOrderBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orders[position]
        holder.binding.orderIdTxt.text = "Order ID: ${order.orderId}"
        holder.binding.totalTxt.text = "Total: $${order.totalPrice}"

        if (order.status == "Delivered") {
            holder.binding.statusTxt.text = "Delivered"
            holder.binding.timerTxt.text = ""
        } else {
            holder.binding.statusTxt.text = "Processing"
            val remainingTime = order.timestamp + (5 * 60 * 1000) - System.currentTimeMillis()
            if (remainingTime > 0) {
                object : CountDownTimer(remainingTime, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                        val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(minutes)
                        holder.binding.timerTxt.text = "Arrives in %02d:%02d".format(minutes, seconds)
                    }

                    override fun onFinish() {
                        holder.binding.statusTxt.text = "Delivered"
                        holder.binding.timerTxt.text = ""
                    }
                }.start()
            } else {
                holder.binding.statusTxt.text = "Delivered"
                holder.binding.timerTxt.text = ""
            }
        }
    }

    override fun getItemCount(): Int = orders.size

    inner class ViewHolder(val binding: ViewholderOrderBinding) : RecyclerView.ViewHolder(binding.root)
}