package uz.os3ketchup.myhelper.domain

import androidx.lifecycle.MutableLiveData

class GetCategoryList(private val repository: CategoryRepository) {
    fun getCategoryList(list: MutableLiveData<List<Category>>){
        return repository.getListCategory(list)
    }
}