package uz.os3ketchup.myhelper.domain

import javax.inject.Inject

class GetUserUseCase @Inject constructor (private val repository: UserRepository) {
    fun getUser(){
        return repository.getUser()
    }
}