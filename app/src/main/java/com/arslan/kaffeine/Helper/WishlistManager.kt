package com.arslan.kaffeine.Helper

import android.content.Context
import android.widget.Toast
import com.arslan.kaffeine.Domain.ItemsModel

class WishlistManager(private val context: Context) {

    private val tinyDB = TinyDB(context)

    fun insertItem(item: ItemsModel) {
        val listWish = getWishlist()
        val existAlready = listWish.any { it.title == item.title }

        if (!existAlready) {
            listWish.add(item)
            tinyDB.putListObject("Wishlist", listWish)
            Toast.makeText(context, "Added to your Wishlist", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Item already in your Wishlist", Toast.LENGTH_SHORT).show()
        }
    }

    fun getWishlist(): ArrayList<ItemsModel> {
        return tinyDB.getListObject("Wishlist") ?: arrayListOf()
    }

    fun removeItem(item: ItemsModel) {
        val listWish = getWishlist()
        listWish.removeAll { it.title == item.title }
        tinyDB.putListObject("Wishlist", listWish)
        Toast.makeText(context, "Removed from your Wishlist", Toast.LENGTH_SHORT).show()
    }
}