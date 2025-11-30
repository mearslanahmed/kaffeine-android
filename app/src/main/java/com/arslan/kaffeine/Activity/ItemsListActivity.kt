package com.arslan.kaffeine.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.arslan.kaffeine.Adapter.ItemListAdapter
import com.arslan.kaffeine.databinding.ActivityItemsListBinding
import com.arslan.kaffeine.viewmodel.MainViewModel

class ItemsListActivity : AppCompatActivity() {
    lateinit var binding: ActivityItemsListBinding
    private lateinit var viewModel: MainViewModel
    private var id: String = ""
    private var title: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityItemsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        getBundles()
        initList()
    }

    private fun initList() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.loadItems(id).observe(this) { items ->
            binding.listView.layoutManager = GridLayoutManager(this@ItemsListActivity, 2)
            binding.listView.adapter = ItemListAdapter(items)
            binding.progressBar.visibility = View.GONE
        }
        binding.backBtn.setOnClickListener { finish() }
    }

    private fun getBundles() {
        id = intent.getStringExtra("id") ?: ""
        title = intent.getStringExtra("title")
        binding.categoryTxt.text = title
    }
}
