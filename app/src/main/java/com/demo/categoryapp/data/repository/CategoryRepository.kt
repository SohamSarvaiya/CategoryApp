package com.demo.categoryapp.data.repository

import com.demo.categoryapp.data.api.InterfaceAPI
import com.demo.categoryapp.domain.models.CategoryListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val interfaceAPI: InterfaceAPI) {

    private val _catList = MutableStateFlow<List<CategoryListItem>>(emptyList())
    val catList: StateFlow<List<CategoryListItem>>
        get() = _catList

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories

    suspend fun getCatList(category:String) {
        val result = interfaceAPI.getCatList("tweets[?(@.category==\"$category\")]")
        if (result.isSuccessful && result.body() != null) {
            _catList.emit(result.body()!!)
        }
    }

    suspend fun getCategories() {
        val result = interfaceAPI.getCategories()
        if (result.isSuccessful && result.body() != null) {
            _categories.emit(result.body()!!)
        }
    }


}