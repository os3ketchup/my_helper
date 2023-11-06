package uz.os3ketchup.myhelper.domain

class InsertCategory(private val repository: CategoryRepository) {
    fun insertCategory(category: Category) {
        repository.insertCategory(category)
    }
}