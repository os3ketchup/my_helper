package uz.os3ketchup.myhelper.domain

import javax.inject.Inject

class DeleteCategoryUseCase @Inject constructor(private val repository: CategoryRepository) {
    fun deleteCategory(category: Category){
        repository.deleteCategory(category)
    }
}