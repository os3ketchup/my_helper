package uz.os3ketchup.myhelper.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.os3ketchup.myhelper.domain.GetUserListUseCase
import uz.os3ketchup.myhelper.domain.GetUserUseCase
import uz.os3ketchup.myhelper.domain.InsertUserUseCase
import uz.os3ketchup.myhelper.domain.User
import uz.os3ketchup.myhelper.domain.UserRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val repository: UserRepository,
    private val insertUserUseCase: InsertUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getUserListUseCase: GetUserListUseCase
) : ViewModel() {

    private val _list = MutableLiveData<List<User>>()
    val list: LiveData<List<User>> get() = _list


    init {
        getList()
    }

    fun insertUser(userName: String?, photoUrl: String?) {
        val user = User(uId = repository.userId!!, name = userName!!, photoUrl = photoUrl!!)
        insertUserUseCase.insertUserName(user)
    }


    private fun getList() {
        getUserListUseCase.getUserList(list = _list)
    }


}
