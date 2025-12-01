package com.arslan.kaffeine.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.arslan.kaffeine.Adapter.CategoryAdapter
import com.arslan.kaffeine.Adapter.PopularAdapter
import com.arslan.kaffeine.databinding.ActivityMainBinding
import com.arslan.kaffeine.viewmodel.MainViewModel
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initBanner()
        initCategory()
        initPopular()
        initBottomMenu()
    }

    private fun initBottomMenu() {
        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    private fun initPopular() {
        binding.progressBarPopular.visibility = View.VISIBLE
        viewModel.loadPopular().observe(this) {
            binding.popularCoffeesView.layoutManager = GridLayoutManager(this, 2)
            binding.popularCoffeesView.adapter = PopularAdapter(it)
            binding.progressBarPopular.visibility = View.GONE
        }
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.loadBanner().observe(this) {
            if (it.isNotEmpty()) {
                Glide.with(this@MainActivity)
                    .load(it[0].url)
                    .into(binding.banner)
            }
            binding.progressBarBanner.visibility = View.GONE
        }
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.loadCategory().observe(this) {
            binding.categoryView.layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            binding.categoryView.adapter = CategoryAdapter(it)
            binding.progressBarCategory.visibility = View.GONE
        }
    }
}
