package uz.os3ketchup.myhelper.domain

class GetUserListUseCase(private val repository: UserRepository) {
    fun getUserList():List<User>{
        return repository.getUserList()
    }
}