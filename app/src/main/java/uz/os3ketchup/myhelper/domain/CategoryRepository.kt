package uz.os3ketchup.myhelper.domain

import androidx.lifecycle.MutableLiveData

interface CategoryRepository {

    fun deleteCategory(categoryId:String)
    fun insertCategory(category: Category)
    fun getListCategory(list: MutableLiveData<List<Category>>)
}