package uz.os3ketchup.myhelper.domain

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DatabaseReference

interface OrderRepository {

    val userId: String?
    fun deleteOrder(order: Order)
    fun insertOrder(order: Order)
    fun getListOrder(list: MutableLiveData<List<Order>>)
}