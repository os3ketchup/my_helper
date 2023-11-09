package uz.os3ketchup.myhelper.domain

import javax.inject.Inject

class InsertCategoryUseCase @Inject constructor (private val repository: CategoryRepository) {
    fun insertCategory(category: Category) {
        repository.insertCategory(category)
    }
}