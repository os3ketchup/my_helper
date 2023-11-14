package uz.os3ketchup.myhelper.data

import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import uz.os3ketchup.myhelper.di.DataModule.Companion.CATEGORIES_REF
import uz.os3ketchup.myhelper.domain.Category
import uz.os3ketchup.myhelper.domain.CategoryRepository
import uz.os3ketchup.myhelper.domain.User
import java.security.PrivateKey
import javax.inject.Inject
import javax.inject.Named

class CategoryRepositoryImpl @Inject constructor(
    @Named(CATEGORIES_REF) private val categoryReference: DatabaseReference,
    private val firebase: Firebase
) : CategoryRepository {


    override val userId: String?
        get() = firebase.auth.currentUser?.uid

    override fun deleteCategory(category: Category) {
        categoryReference.child(category.name!!).removeValue()
    }


    override fun insertCategory(category: Category) {
        categoryReference.child(category.name!!).setValue(category)
    }

    override fun getListCategory(list: MutableLiveData<List<Category>>) {
        val eventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataList = mutableListOf<Category>()
                for (child in snapshot.children) {
                    val category = child.getValue(Category::class.java)
                    category?.let {
                        dataList.add(it)
                    }
                    list.postValue(dataList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        }
        categoryReference.addValueEventListener(eventListener)
    }

}