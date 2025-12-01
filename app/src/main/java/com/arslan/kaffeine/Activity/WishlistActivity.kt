package com.arslan.kaffeine.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arslan.kaffeine.Adapter.WishlistAdapter
import com.arslan.kaffeine.Helper.WishlistManager
import com.arslan.kaffeine.databinding.ActivityWishlistBinding

class WishlistActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWishlistBinding
    private lateinit var wishlistManager: WishlistManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWishlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        wishlistManager = WishlistManager(this)

        binding.backBtn.setOnClickListener { finish() }

        initWishlist()
    }

    private fun initWishlist() {
        val wishlistItems = wishlistManager.getWishlist()

        if (wishlistItems.isEmpty()) {
            binding.emptyTxt.visibility = View.VISIBLE
            binding.wishlistView.visibility = View.GONE
        } else {
            binding.emptyTxt.visibility = View.GONE
            binding.wishlistView.visibility = View.VISIBLE
            binding.wishlistView.layoutManager = LinearLayoutManager(this)
            binding.wishlistView.adapter = WishlistAdapter(wishlistItems, this, wishlistManager)
        }
    }
}