package uz.os3ketchup.myhelper.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.os3ketchup.myhelper.domain.Category
import uz.os3ketchup.myhelper.domain.DeleteCategoryUseCase
import uz.os3ketchup.myhelper.domain.GetCategoryListUseCase
import uz.os3ketchup.myhelper.domain.GetUserListUseCase
import uz.os3ketchup.myhelper.domain.InsertCategoryUseCase
import uz.os3ketchup.myhelper.domain.InsertUserUseCase
import uz.os3ketchup.myhelper.domain.User
import uz.os3ketchup.myhelper.domain.UserRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: UserRepository,
    private val insertUserUseCase: InsertUserUseCase,
    private val getUserListUseCase: GetUserListUseCase,
    private val insertCategory: InsertCategoryUseCase,
    private val getCategoryList: GetCategoryListUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase
) : ViewModel() {

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> get() = _userList

    private val _categoryList = MutableLiveData<List<Category>>()
    val categoryList: LiveData<List<Category>> get() = _categoryList


    init {
        getUserList()
        getCategoryList()
    }


    fun deleteCategory(categoryName: String) {
        val list = _categoryList.value.orEmpty().toMutableList()
        list.let {
            it.forEach { category ->
                if (categoryName == category.name) {
                    deleteCategoryUseCase.deleteCategory(category)
                    list.remove(category)
                    _categoryList.postValue(list)
                    return
                }
            }

        }

    }

    fun insertCategory(categoryName: String?, photoUrl: String?) {
        val category = Category(
            id = repository.userId!!,
            name = categoryName!!,
            photoUrl = photoUrl!!
        )
        insertCategory.insertCategory(category)
    }

    fun insertUser(userName: String?, photoUrl: String?) {
        val user = User(
            uId = repository.userId!!,
            name = userName!!,
            photoUrl = photoUrl!!
        )
        insertUserUseCase.insertUserName(user)
    }

    private fun getUserList() {
        getUserListUseCase.getUserList(list = _userList)
    }
    private fun getCategoryList() {
        getCategoryList.getCategoryList(list = _categoryList)
    }

}
