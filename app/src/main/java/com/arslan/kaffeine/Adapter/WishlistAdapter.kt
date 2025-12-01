package com.arslan.kaffeine.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arslan.kaffeine.Domain.ItemsModel
import com.arslan.kaffeine.Helper.WishlistManager
import com.arslan.kaffeine.databinding.ViewholderWishlistBinding
import com.bumptech.glide.Glide

class WishlistAdapter(
    private val items: ArrayList<ItemsModel>,
    private val context: Context,
    private val wishlistManager: WishlistManager
) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderWishlistBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.titleTxt.text = item.title
        holder.binding.priceTxt.text = "$${item.price}"

        Glide.with(context)
            .load(item.picUrl[0])
            .into(holder.binding.pic)

        holder.binding.removeBtn.setOnClickListener {
            wishlistManager.removeItem(item)
            items.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val binding: ViewholderWishlistBinding) : RecyclerView.ViewHolder(binding.root)
}