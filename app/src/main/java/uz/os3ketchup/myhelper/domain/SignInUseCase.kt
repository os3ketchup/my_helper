package uz.os3ketchup.bozorhelper.domain

import uz.os3ketchup.myhelper.domain.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repository: AuthRepository) {
    fun signIn() {
        repository.signIn()
    }
}