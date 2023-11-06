package uz.os3ketchup.myhelper.data

import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import uz.os3ketchup.myhelper.domain.Category
import uz.os3ketchup.myhelper.domain.CategoryRepository
import uz.os3ketchup.myhelper.domain.User

class CategoryRepositoryImpl(
    private val usersReference: DatabaseReference
) : CategoryRepository {
    override fun deleteCategory(categoryId: String) {

    }

    override fun insertCategory(category: Category) {

    }

    override fun getListCategory(list: MutableLiveData<List<Category>>) {
        val eventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataList = mutableListOf<Category>()
                for (child in snapshot.children){
                    val category = child.getValue(Category::class.java)
                    category?.let {
                        dataList.add(it)
                    }
                    list.postValue(dataList)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        usersReference.addValueEventListener(eventListener)
    }

}