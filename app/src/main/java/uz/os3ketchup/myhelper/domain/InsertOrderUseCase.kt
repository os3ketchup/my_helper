package uz.os3ketchup.myhelper.domain

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class InsertOrderUseCase @Inject constructor(private val repository: OrderRepository) {
    fun insertProduct(
        list: List<Product>,
        currentTime: String,
        toUId: String
    ) {
        repository.insertOrder(list, currentTime, toUId)
    }
}