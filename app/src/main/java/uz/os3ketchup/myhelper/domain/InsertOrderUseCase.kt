package uz.os3ketchup.myhelper.domain

import javax.inject.Inject

class InsertOrderUseCase @Inject constructor(private val repository: OrderRepository) {
    fun insertProduct(order: Order) {
        repository.insertOrder(order)
    }
}