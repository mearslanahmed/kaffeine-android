package com.arslan.kaffeine.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.arslan.kaffeine.Activity.ItemsListActivity
import com.arslan.kaffeine.Domain.CategoryModel
import com.arslan.kaffeine.R
import com.arslan.kaffeine.databinding.ViewholderCategoryBinding


class CategoryAdapter(val items: MutableList<CategoryModel>):
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){
    private lateinit var context: Context
    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    inner class ViewHolder(val binding: ViewholderCategoryBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.ViewHolder {
        context = parent.context
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(context),
            parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = items[position]
        holder.binding.titleCat.text = item.title

        if (selectedPosition == position){
            holder.binding.titleCat.setBackgroundResource(R.drawable.brown_full_corner_bg)
            holder.binding.titleCat.setTextColor(ContextCompat.getColor(context, R.color.white))
        }else{
            holder.binding.titleCat.setBackgroundResource(R.drawable.white_full_corner_bg)
            holder.binding.titleCat.setTextColor(ContextCompat.getColor(context, R.color.darkBrown))
        }

        holder.binding.root.setOnClickListener {
            val adapterPosition = holder.adapterPosition
            if (adapterPosition == RecyclerView.NO_POSITION) {
                return@setOnClickListener
            }
            lastSelectedPosition = selectedPosition
            selectedPosition = adapterPosition
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            val clickedItem = items[adapterPosition]
            val intent = Intent(context, ItemsListActivity::class.java).apply {
                putExtra("id", clickedItem.id.toString())
                putExtra("title", clickedItem.title)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}