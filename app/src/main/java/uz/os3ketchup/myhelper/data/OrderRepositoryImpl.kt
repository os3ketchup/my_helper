package uz.os3ketchup.myhelper.data

import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import uz.os3ketchup.myhelper.di.DataModule
import uz.os3ketchup.myhelper.domain.Order
import uz.os3ketchup.myhelper.domain.OrderRepository
import uz.os3ketchup.myhelper.domain.Product
import javax.inject.Inject
import javax.inject.Named

class OrderRepositoryImpl @Inject constructor(
    @Named(DataModule.ORDERS_REF) private val orderReference: DatabaseReference,
    private val firebase: Firebase
) : OrderRepository {
    override val userId: String
        get() = firebase.auth.uid!!

    override fun deleteOrder(order: Order) {
        orderReference.child(order.id).removeValue()
    }

    override fun insertOrder(
        list: List<Product>,
        currentTime: String,
        toUId: String
    ) {
        val order = Order(orderReference.push().key!!, userId, list, currentTime, toUId)
        orderReference.child(order.id).setValue(order)
    }


    override fun getListOrder(list: MutableLiveData<List<Order>>) {
        val eventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataList = mutableListOf<Order>()
                for (child in snapshot.children) {
                    val category = child.getValue(Order::class.java)
                    category?.let {
                        dataList.add(it)
                    }
                    list.postValue(dataList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        orderReference.addValueEventListener(eventListener)

    }
}

