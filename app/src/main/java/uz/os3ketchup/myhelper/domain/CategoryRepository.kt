package uz.os3ketchup.myhelper.domain

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DatabaseReference

interface CategoryRepository {

    val userId: String?
    fun deleteCategory(category: Category)
    fun insertCategory(category: Category)
    fun getListCategory(list: MutableLiveData<List<Category>>)
}