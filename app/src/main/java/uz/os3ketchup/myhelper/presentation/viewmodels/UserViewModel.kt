package uz.os3ketchup.myhelper.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import uz.os3ketchup.myhelper.domain.User
import uz.os3ketchup.myhelper.domain.UserRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {


    fun insertUser(userName: String?, photoUrl: String?) {
        val user = User(uId = repository.userId!!, name = userName!!, photoUrl = photoUrl!!)
        repository.insertUser(user)
    }


}
