package uz.os3ketchup.myhelper.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import uz.os3ketchup.myhelper.AuthApp
import uz.os3ketchup.myhelper.R
import uz.os3ketchup.myhelper.databinding.ActivityMainBinding
import uz.os3ketchup.myhelper.presentation.fragments.ListFragment
import uz.os3ketchup.myhelper.presentation.fragments.MainFragment
import uz.os3ketchup.myhelper.presentation.viewmodels.MainViewModel
import uz.os3ketchup.myhelper.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    private val component by lazy {
        (application as AuthApp).component
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val visibility = when (destination.id) {
                R.id.userFragment -> View.GONE
                R.id.chatFragment -> View.GONE
                R.id.mainFragment -> View.VISIBLE
                R.id.listFragment-> View.VISIBLE
                else -> {
                    View.GONE
                }
            }
            binding.bottomNavigation.visibility = visibility
        }

    }
}

