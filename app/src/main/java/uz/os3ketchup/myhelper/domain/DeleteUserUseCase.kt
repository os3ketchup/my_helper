package uz.os3ketchup.myhelper.domain

class DeleteUserUseCase(private val repository: UserRepository) {
    fun deletePhotoUser(uId: String) {
        repository.deleteUser(uId)
    }
}