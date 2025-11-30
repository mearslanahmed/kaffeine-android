package com.arslan.kaffeine.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arslan.kaffeine.Domain.ItemsModel
import com.arslan.kaffeine.databinding.ViewholderPopularBinding
import com.bumptech.glide.Glide

class PopularAdapter(val items: MutableList<ItemsModel>):
    RecyclerView.Adapter<PopularAdapter.ViewHolder>(){

    lateinit var context: Context

    inner class ViewHolder(var binding: ViewholderPopularBinding):
    RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularAdapter.ViewHolder {
        context = parent.context
        val binding = ViewholderPopularBinding.inflate(LayoutInflater.from(context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularAdapter.ViewHolder, position: Int) {
        holder.binding.titleTxt.text = items[position].title
        holder.binding.priceTxt.text = "Rp-"+items[position].price.toString()
        holder.binding.subtitleTxt.text = items[position].extra.toString()

        Glide.with(context)
            .load(items[position].picUrl[0])
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int = items.size
}