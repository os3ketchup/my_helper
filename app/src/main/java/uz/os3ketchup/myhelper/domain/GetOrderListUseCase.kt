package uz.os3ketchup.myhelper.domain

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOrderListUseCase @Inject constructor(private val repository: OrderRepository) {
    fun getOrderList(list: MutableLiveData<List<Order>>) {
        return repository.getListOrder(list)
    }
}