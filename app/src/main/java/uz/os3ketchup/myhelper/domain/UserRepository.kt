package uz.os3ketchup.myhelper.domain

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.ValueEventListener

interface UserRepository {

    val userId: String?
     fun insertUser(user: User)
     fun deleteUser(uId: String)
     fun getUser()
     fun getUserList(list: MutableLiveData<List<User>>)
}