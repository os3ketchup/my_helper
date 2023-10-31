package uz.os3ketchup.myhelper.domain

class GetUserUseCase(private val repository: UserRepository) {
    fun getUser(userId:String):User{
        return repository.getUser(userId)
    }
}