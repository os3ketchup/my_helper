package uz.os3ketchup.myhelper.data

import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.database
import uz.os3ketchup.myhelper.domain.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val firebase: Firebase) : AuthRepository {


    override val currentUser: FirebaseAuth
        get() = firebase.auth


}