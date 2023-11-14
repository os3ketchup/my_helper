package uz.os3ketchup.myhelper.presentation.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.os3ketchup.myhelper.AuthApp
import uz.os3ketchup.myhelper.R
import uz.os3ketchup.myhelper.databinding.FragmentMainBinding
import uz.os3ketchup.myhelper.domain.Category
import uz.os3ketchup.myhelper.domain.User
import uz.os3ketchup.myhelper.presentation.adapters.CategoryListAdapter
import uz.os3ketchup.myhelper.presentation.adapters.CategoryListAdapter.Companion.VIEW_TYPE_CATEGORY
import uz.os3ketchup.myhelper.presentation.adapters.UserListAdapter
import uz.os3ketchup.myhelper.presentation.adapters.UserListAdapter.Companion.MAX_POOL_SIZE
import uz.os3ketchup.myhelper.presentation.adapters.UserListAdapter.Companion.VIEW_TYPE_USER
import uz.os3ketchup.myhelper.presentation.viewmodels.MainViewModel
import uz.os3ketchup.myhelper.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding==null")

    private lateinit var userAdapter: UserListAdapter
    private lateinit var categoryAdapter: CategoryListAdapter

    private val component by lazy {
        (requireActivity().application as AuthApp).component
    }

    private lateinit var viewModel: MainViewModel

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
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        setupRecyclerView()
        setupClickListener()
        viewModel.userList.observe(viewLifecycleOwner) {
            userAdapter.submitList(it)
        }
        viewModel.categoryList.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it)
            if (it.isEmpty()) {
                binding.rvCategory.visibility = View.GONE
                binding.cdCategory.visibility = View.VISIBLE
            } else {
                binding.rvCategory.visibility = View.VISIBLE
                binding.cdCategory.visibility = View.GONE
            }
        }
        binding.btnAddCategory.setOnClickListener {
            buildDialog()
        }


    }

    private fun launchChatFragment(user: User) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToChatFragment(user))
    }

    private fun setupRecyclerView() {
        with(binding.rvUser) {
            userAdapter = UserListAdapter()
            adapter = userAdapter
            recycledViewPool.setMaxRecycledViews(VIEW_TYPE_USER, MAX_POOL_SIZE)
        }
        with(binding.rvCategory) {
            categoryAdapter = CategoryListAdapter()
            adapter = categoryAdapter
            recycledViewPool.setMaxRecycledViews(VIEW_TYPE_CATEGORY, MAX_POOL_SIZE)
        }
    }

    private fun setupClickListener() {
        userAdapter.onUserItemClickListener = { user ->
            launchChatFragment(user)
        }
        categoryAdapter.onCategoryItemClickListener = { category ->
            launchCategoryFragment(category)
        }
        categoryAdapter.onPopupMenuClickListener = { binding, category ->
            val popup = PopupMenu(requireContext(), binding.btnMore)
            popup.menuInflater.inflate(R.menu.category_popup, popup.menu)
            popup.setOnMenuItemClickListener { menu ->
                when (menu.itemId) {
                    R.id.add_action -> {
                        buildDialog()
                        true
                    }

                    R.id.delete_action -> {
                        viewModel.deleteCategory(category.name!!)
                        Toast.makeText(requireContext(), "delete", Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.edit_action -> {
                        Toast.makeText(requireContext(), "edit", Toast.LENGTH_SHORT).show()
                        true
                    }

                    else -> {
                        false
                    }

                }
            }
            popup.show()
        }
    }

    private fun launchCategoryFragment(category:Category) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToCategoryFragment(category))
    }

    private fun buildDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(
            R.layout.dialog_answer
        )
        val yesBtn = dialog.findViewById<Button>(R.id.btn_yes)
        val noBtn = dialog.findViewById<Button>(R.id.btn_no)
        val et = dialog.findViewById<EditText>(R.id.et_category_name)
        yesBtn.setOnClickListener {
            viewModel.insertCategory(categoryName = et.text.toString(), "dassa")
            dialog.cancel()
        }
        noBtn.setOnClickListener {
            dialog.cancel()
        }
        dialog.show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}