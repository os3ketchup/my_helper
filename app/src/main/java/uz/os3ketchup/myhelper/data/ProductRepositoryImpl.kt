package uz.os3ketchup.myhelper.data

import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uz.os3ketchup.myhelper.di.DataModule
import uz.os3ketchup.myhelper.domain.Category
import uz.os3ketchup.myhelper.domain.Product
import uz.os3ketchup.myhelper.domain.ProductRepository
import javax.inject.Inject
import javax.inject.Named

class ProductRepositoryImpl @Inject constructor(
    @Named(DataModule.PRODUCTS_REF) override val productReference: DatabaseReference

) : ProductRepository {

    override fun insertProduct(
        name: String,
        price: String,
        unit: String
    ) {
        val product = Product(id = productReference.push().key!!, name = name, unit =  unit, price =  price)
        productReference.child(product.id).setValue(product)
    }

    override fun deleteProduct(product: Product) {
        productReference.child(product.id).removeValue()
    }

    override fun getProductList(list: MutableLiveData<List<Product>>) {
        val eventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataList = mutableListOf<Product>()
                for (child in snapshot.children) {
                    val category = child.getValue(Product::class.java)
                    category?.let {
                        dataList.add(it)
                    }
                    list.postValue(dataList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        productReference.addValueEventListener(eventListener)

    }
}