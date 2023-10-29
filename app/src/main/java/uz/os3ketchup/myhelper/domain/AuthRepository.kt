package uz.os3ketchup.myhelper.domain

import com.google.firebase.auth.FirebaseAuth

interface AuthRepository {
    val currentUser: FirebaseAuth
}