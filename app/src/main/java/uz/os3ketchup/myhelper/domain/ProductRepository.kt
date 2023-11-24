package uz.os3ketchup.myhelper.domain

import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference

interface ProductRepository {
     val productReference: DatabaseReference
    fun insertProduct(name:String,price:String,unit:String,category: Category)
    fun deleteProduct(product: Product)
    fun getProductList(list: MutableLiveData<List<Product>>)
}