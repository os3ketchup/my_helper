package uz.os3ketchup.myhelper.domain

import androidx.lifecycle.MutableLiveData

interface OrderRepository {

    val userId: String?
    fun deleteOrder(order: Order)
    fun insertOrder(list:List<Product>,currentTime:String,toUId:String)
    fun getListOrder(list: MutableLiveData<List<Order>>)
}