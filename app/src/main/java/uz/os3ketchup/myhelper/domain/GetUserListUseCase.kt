package uz.os3ketchup.myhelper.domain

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(private val repository: UserRepository) {
    fun getUserList(list: MutableLiveData<List<User>>) {
        return repository.getUserList(list)
    }
}