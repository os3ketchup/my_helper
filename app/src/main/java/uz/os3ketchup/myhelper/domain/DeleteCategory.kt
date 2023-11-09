package uz.os3ketchup.myhelper.domain

import javax.inject.Inject

class DeleteCategory @Inject constructor(private val repository: CategoryRepository) {
    fun deleteCategory(categoryId:String){
        repository.deleteCategory(categoryId)
    }
}