package uz.os3ketchup.myhelper.domain

import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(private val repository: UserRepository) {
    fun deletePhotoUser(uId: String) {
        repository.deleteUser(uId)
    }
}