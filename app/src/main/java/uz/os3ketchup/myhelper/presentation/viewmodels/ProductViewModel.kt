package uz.os3ketchup.myhelper.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.os3ketchup.myhelper.domain.Category
import uz.os3ketchup.myhelper.domain.DeleteProductUseCase
import uz.os3ketchup.myhelper.domain.GetProductListUseCase
import uz.os3ketchup.myhelper.domain.InsertProductUseCase
import uz.os3ketchup.myhelper.domain.Product
import uz.os3ketchup.myhelper.domain.ProductRepository
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val insertProduct: InsertProductUseCase,
    private val deleteProduct: DeleteProductUseCase,
    private val getProductList: GetProductListUseCase,
    private val repository: ProductRepository
) : ViewModel() {

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> get() = _productList


    init {
        getProductList()
    }

    private fun getProductList() {
        getProductList.getUserList(list = _productList)
    }

    fun deleteProduct(product: Product) {
        deleteProduct.deleteCategory(product)
    }

    fun insertProduct(productName:String,productPrice:String,productUnit:String) {
        val product = Product(
           name = productName,
            price = productPrice,
            unit = productUnit,
           id = repository.productReference.push().key!!
        )
        insertProduct.insertProduct(product.copy())
    }


}