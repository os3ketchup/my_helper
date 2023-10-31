package uz.os3ketchup.myhelper.domain

class InsertUserUseCase(private val repository: UserRepository) {
    fun insertUserName(user: User) {
        repository.insertUser(user)
    }
}