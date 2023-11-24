package uz.os3ketchup.myhelper.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.os3ketchup.myhelper.domain.Category
import uz.os3ketchup.myhelper.domain.DeleteOrderUseCase
import uz.os3ketchup.myhelper.domain.DeleteProductUseCase
import uz.os3ketchup.myhelper.domain.GetOrderListUseCase
import uz.os3ketchup.myhelper.domain.GetProductListUseCase
import uz.os3ketchup.myhelper.domain.InsertOrderUseCase
import uz.os3ketchup.myhelper.domain.InsertProductUseCase
import uz.os3ketchup.myhelper.domain.Order
import uz.os3ketchup.myhelper.domain.OrderRepository
import uz.os3ketchup.myhelper.domain.Product
import uz.os3ketchup.myhelper.domain.ProductRepository
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val insertProduct: InsertProductUseCase,
    private val deleteProduct: DeleteProductUseCase,
    private val getProductList: GetProductListUseCase,
    private val repository: ProductRepository,

    private val insertOrderUseCase: InsertOrderUseCase,
    private val deleteOrderUseCase: DeleteOrderUseCase,
    private val getOrderListUseCase: GetOrderListUseCase,
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> get() = _productList

    private val _orderList = MutableLiveData<List<Order>>()
    val orderList: LiveData<List<Order>> get() = _orderList

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean> get() = _errorInputName

    private val _errorInputPrice = MutableLiveData<Boolean>()
    val errorInputPrice: LiveData<Boolean> get() = _errorInputPrice

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> get() = _product


    init {
        getProductList()
        getOrderList()

    }

    private fun getProductList() {
        getProductList.getUserList(list = _productList)
    }

    fun deleteProduct(product: Product) {
        deleteProduct.deleteCategory(product)
    }

    fun insertProduct(
        inputName: String?,
        inputPrice: Double?,
        inputUnit: String?,
        category: Category?
    ) {
        val productName = parseName(inputName)
        val productPrice = parsePrice(inputPrice.toString())
        val fieldsValid = validateInput(productName, productPrice)
        if (fieldsValid) {

                    insertProduct.insertProduct( name = productName,
                        category = category!!,
                        price = productPrice.toString(),
                        unit =inputUnit!!)

        }
    }

    private fun validateInput(productName: String, productPrice: Double): Boolean {
        var result = true
        if (productName.isBlank()) {
            _errorInputName.value = true
            result = false
        }

        if (productPrice <= 0) {
            _errorInputPrice.value = true
            result = false
        }
        return result
    }

    private fun parsePrice(inputPrice: String?): Double {
        return try {
            inputPrice?.trim()?.toDouble() ?: 0.0
        } catch (e: Exception) {
            0.0
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }


    fun insertOrder(
        productList: List<Product>,
        currentTime: String,
        toUId: String,
    ) {
        insertOrderUseCase.insertProduct(productList, currentTime, toUId)
    }


    private fun getOrderList() {
        getOrderListUseCase.getOrderList(list = _orderList)
    }


}