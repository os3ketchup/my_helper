package uz.os3ketchup.myhelper.domain

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(private val repository: ProductRepository) {
    fun getUserList(list: MutableLiveData<List<Product>>) {
        return repository.getProductList(list)
    }
}