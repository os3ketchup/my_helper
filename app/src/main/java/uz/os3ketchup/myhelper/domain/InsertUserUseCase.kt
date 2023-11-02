package uz.os3ketchup.myhelper.domain

import javax.inject.Inject

class InsertUserUseCase @Inject constructor (private val repository: UserRepository) {
    fun insertUserName(user: User) {
        repository.insertUser(user)
    }
}