package uz.os3ketchup.myhelper.data

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import uz.os3ketchup.myhelper.domain.User
import uz.os3ketchup.myhelper.domain.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val usersReference: DatabaseReference,
    private val firebase:Firebase
) : UserRepository {


    override val userId: String?
        get() = firebase.auth.currentUser?.uid

    override fun insertUser(user: User) {
        usersReference.child(user.uId).setValue(user)
    }

    override fun deleteUser(uId: String) {
        usersReference.child(uId).removeValue()
    }


    override fun getUser(userId: String): User {
        var user = User()
        getUserList().forEach {
            if (it.uId == userId) {
                user = it
            }
        }
        return user
    }

    override fun getUserList(): List<User> {
        val list = ArrayList<User>()
        userId?.let {
            val callback = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (child in snapshot.children) {
                        val user = child.getValue(User::class.java)
                        user?.let {
                            if (it.uId != userId) {
                                list.add(it)
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            }

            usersReference.addValueEventListener(callback)
        }
        return list

    }

}