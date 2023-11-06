package uz.os3ketchup.myhelper.domain

class DeleteCategory(private val repository: CategoryRepository) {
    fun deleteCategory(categoryId:String){
        repository.deleteCategory(categoryId)
    }
}