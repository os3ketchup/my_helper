package uz.os3ketchup.myhelper.presentation.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import uz.os3ketchup.myhelper.AuthApp
import uz.os3ketchup.myhelper.databinding.FragmentUserBinding
import uz.os3ketchup.myhelper.di.DataModule.Companion.USERS_REF
import uz.os3ketchup.myhelper.domain.User
import uz.os3ketchup.myhelper.presentation.MainActivity
import uz.os3ketchup.myhelper.presentation.viewmodels.UserViewModel
import uz.os3ketchup.myhelper.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject


class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding: FragmentUserBinding
        get() = _binding ?: throw RuntimeException("FragmentUserBinding == null")


    private lateinit var viewModel: UserViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as AuthApp).component
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]
        binding.btnUser.setOnClickListener {
            val userName = binding.etUser.text.toString()
            viewModel.insertUser(userName = userName, photoUrl = "")

            launchMainActivity()
        }
    }


    private fun launchMainActivity() {
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.root


    }

}