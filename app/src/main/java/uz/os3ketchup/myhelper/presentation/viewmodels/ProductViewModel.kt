package uz.os3ketchup.myhelper.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _customProductList = MutableLiveData<List<Product>>()
    val customProductList:LiveData<List<Product>> get() = _customProductList


    init {
        getProductList()
        getOrderList()

    }

    private fun getProductList() {
        getProductList.getUserList(list = _productList)
    }

     fun getCustomProductList(customList:MutableList<Product>){
        _customProductList.value = customList
    }

    fun deleteProduct(product: Product) {
        deleteProduct.deleteCategory(product)
    }

    fun insertProduct(productName: String, productPrice: String, productUnit: String) {

        insertProduct.insertProduct(productName, productPrice, productUnit)
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