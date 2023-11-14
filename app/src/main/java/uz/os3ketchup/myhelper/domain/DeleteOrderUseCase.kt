package uz.os3ketchup.myhelper.domain

import javax.inject.Inject

class DeleteOrderUseCase @Inject constructor(private val repository: OrderRepository) {
    fun deletePhotoUser(order: Order) {
        repository.deleteOrder(order)
    }
}