package uz.os3ketchup.myhelper.domain

import javax.inject.Inject

class InsertProductUseCase @Inject constructor(private val repository: ProductRepository) {
    fun insertProduct(
        name: String,
        price: String,
        unit: String
    ) {
        repository.insertProduct(name, price, unit)
    }
}