package uz.os3ketchup.myhelper.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.os3ketchup.myhelper.domain.Category
import uz.os3ketchup.myhelper.domain.GetCategoryListUseCase
import uz.os3ketchup.myhelper.domain.GetUserListUseCase
import uz.os3ketchup.myhelper.domain.InsertCategoryUseCase
import uz.os3ketchup.myhelper.domain.InsertUserUseCase
import uz.os3ketchup.myhelper.domain.User
import uz.os3ketchup.myhelper.domain.UserRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val repository: UserRepository,
    private val insertUserUseCase: InsertUserUseCase,
    private val getUserListUseCase: GetUserListUseCase,
    private val insertCategory: InsertCategoryUseCase,
    private val getCategoryList: GetCategoryListUseCase
) : ViewModel() {

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> get() = _userList

    private val _categoryList = MutableLiveData<List<Category>>()
    val categoryList: LiveData<List<Category>> get() = _categoryList


    init {
        getUserList()
        getCategoryList()
    }

    private fun getCategoryList() {
        getCategoryList.getCategoryList(list = _categoryList)
    }

    fun insertUser(userName: String?, photoUrl: String?) {
        val user = User(uId = repository.userId!!, name = userName!!, photoUrl = photoUrl!!)
        insertUserUseCase.insertUserName(user)
    }

    fun insertCategory(categoryName: String?, photoUrl: String?) {
        val category = Category(
            id = repository.userId!!,
            name = categoryName!!,
            photoUrl = photoUrl!!
        )
        insertCategory.insertCategory(category)
    }


    private fun getUserList() {
        getUserListUseCase.getUserList(list = _userList)
    }


}
