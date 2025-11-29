package com.arslan.kaffeine.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arslan.kaffeine.Domain.BannerModel
import com.arslan.kaffeine.Domain.CategoryModel
import com.arslan.kaffeine.repository.MainRepository

class MainViewModel: ViewModel() {
    private val repository = MainRepository()

    fun loadBanner(): LiveData<MutableList<BannerModel>>{
        return repository.loadBanner()
    }

    fun loadCategory(): LiveData<MutableList<CategoryModel>>{
        return repository.loadCategory()
    }
}