package uz.os3ketchup.myhelper.domain

import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

interface UserRepository {

    val userId:String?
    fun insertUser(user: User)
    fun deleteUser(uId: String)
    fun getUser(userId: String): User
    fun getUserList(): List<User>

}