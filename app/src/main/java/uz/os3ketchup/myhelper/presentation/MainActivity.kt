package uz.os3ketchup.myhelper.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import uz.os3ketchup.myhelper.AuthApp
import uz.os3ketchup.myhelper.databinding.ActivityMainBinding
import uz.os3ketchup.myhelper.di.DataModule.Companion.USERS_REF
import uz.os3ketchup.myhelper.domain.User
import uz.os3ketchup.myhelper.presentation.viewmodels.UserViewModel
import uz.os3ketchup.myhelper.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: UserViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    private val component by lazy {
        (application as AuthApp).component
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Firebase.database.setPersistenceEnabled(true)
        component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]

        viewModel.list.observe(this){
            binding.tvHello.text = it[0].name
        }


    }
}
