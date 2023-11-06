package uz.os3ketchup.myhelper.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.os3ketchup.myhelper.AuthApp
import uz.os3ketchup.myhelper.R
import uz.os3ketchup.myhelper.databinding.FragmentMainBinding
import uz.os3ketchup.myhelper.domain.User
import uz.os3ketchup.myhelper.presentation.adapters.UserListAdapter
import uz.os3ketchup.myhelper.presentation.adapters.UserListAdapter.Companion.MAX_POOL_SIZE
import uz.os3ketchup.myhelper.presentation.adapters.UserListAdapter.Companion.VIEW_TYPE_USER
import uz.os3ketchup.myhelper.presentation.viewmodels.UserViewModel
import uz.os3ketchup.myhelper.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding==null")

    private lateinit var userAdapter: UserListAdapter

    private val component by lazy {
        (requireActivity().application as AuthApp).component
    }


    private lateinit var viewModel: UserViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]
        setupRecyclerView()
        setupClickListener()
        viewModel.list.observe(viewLifecycleOwner) {
            userAdapter.submitList(it)
        }
    }

    private fun launchFragment(user: User) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToChatFragment(user))
    }

    private fun setupRecyclerView() {
        with(binding.rvUser) {
            userAdapter = UserListAdapter()
            adapter = userAdapter
            recycledViewPool.setMaxRecycledViews(VIEW_TYPE_USER, MAX_POOL_SIZE)
        }
    }

    private fun setupClickListener() {
        userAdapter.onUserItemClickListener = {
            launchFragment(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}