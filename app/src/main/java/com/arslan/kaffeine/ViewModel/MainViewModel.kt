package com.arslan.kaffeine.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arslan.kaffeine.Domain.BannerModel
import com.arslan.kaffeine.Domain.CategoryModel
import com.arslan.kaffeine.Domain.ItemsModel
import com.arslan.kaffeine.repository.MainRepository

class MainViewModel: ViewModel() {
    private val repository = MainRepository()

    fun loadBanner(): LiveData<MutableList<BannerModel>>{
        return repository.loadBanner()
    }

    fun loadCategory(): LiveData<MutableList<CategoryModel>>{
        return repository.loadCategory()
    }

    fun loadPopular(): LiveData<MutableList<ItemsModel>>{
        return repository.loadPopularCoffees()
    }

    fun loadItems(categoryId: String): LiveData<MutableList<ItemsModel>>{
        return repository.loadItemCategory(categoryId)
    }
}