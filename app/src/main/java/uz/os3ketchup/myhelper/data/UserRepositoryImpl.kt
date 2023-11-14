package uz.os3ketchup.myhelper.data

import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import uz.os3ketchup.myhelper.di.DataModule.Companion.USERS_REF
import uz.os3ketchup.myhelper.domain.User
import uz.os3ketchup.myhelper.domain.UserRepository
import javax.inject.Inject
import javax.inject.Named

    class UserRepositoryImpl @Inject constructor(
       @Named(USERS_REF) private val usersReference: DatabaseReference,
        private val firebase: Firebase
    ) : UserRepository {


        override val userId: String?
            get() = firebase.auth.currentUser?. uid

        override fun insertUser(user: User) {
            usersReference.child(userId!!).setValue(user)
        }

        override fun deleteUser(uId: String) {
            usersReference.child(uId).removeValue()
        }


        override fun getUser() {

        }

        override fun getUserList(list:MutableLiveData<List<User>>) {
            val eventListener = object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val dataList = mutableListOf<User>()
                    for (child in snapshot.children){
                        val user = child.getValue(User::class.java)
                        user?.let {
                            dataList.add(user)
                        }
                        list.postValue(dataList)
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            }
            usersReference.addValueEventListener(eventListener)
        }
    }

