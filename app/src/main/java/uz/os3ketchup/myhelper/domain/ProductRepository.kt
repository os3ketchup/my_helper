package uz.os3ketchup.myhelper.domain

import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference

interface ProductRepository {
     val productReference: DatabaseReference
    fun insertProduct(product: Product)
    fun deleteProduct(product: Product)
    fun getProductList(list: MutableLiveData<List<Product>>)
}