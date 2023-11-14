package uz.os3ketchup.myhelper.domain

import javax.inject.Inject

class DeleteProductUseCase @Inject constructor(private val repository: ProductRepository) {
    fun deleteCategory(product: Product){
        repository.deleteProduct(product)
    }
}