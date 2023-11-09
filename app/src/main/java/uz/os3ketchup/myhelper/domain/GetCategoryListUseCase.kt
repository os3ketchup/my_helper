package uz.os3ketchup.myhelper.domain

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class GetCategoryListUseCase @Inject constructor(private val repository: CategoryRepository) {
    fun getCategoryList(list: MutableLiveData<List<Category>>) {
        return repository.getListCategory(list)
    }
}