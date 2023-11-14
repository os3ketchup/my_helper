package uz.os3ketchup.myhelper.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import uz.os3ketchup.myhelper.AuthApp
import uz.os3ketchup.myhelper.R
import uz.os3ketchup.myhelper.databinding.FragmentProductBinding
import uz.os3ketchup.myhelper.presentation.adapters.ProductListAdapter
import uz.os3ketchup.myhelper.presentation.adapters.ProductListAdapter.Companion.MAX_POOL_SIZE
import uz.os3ketchup.myhelper.presentation.adapters.ProductListAdapter.Companion.VIEW_TYPE_PRODUCT
import uz.os3ketchup.myhelper.presentation.viewmodels.MainViewModel
import uz.os3ketchup.myhelper.presentation.viewmodels.ProductViewModel
import uz.os3ketchup.myhelper.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding: FragmentProductBinding
        get() = _binding ?: throw RuntimeException("FragmentProductBinding == null")


    private lateinit var productListAdapter: ProductListAdapter
    private lateinit var spinnerData: Array<String>
    private var selectedText: String = "sss"


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ProductViewModel

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
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[ProductViewModel::class.java]
        setupRecyclerView()
        setupClickListener()
        setSpinner()
        viewModel.productList.observe(viewLifecycleOwner) {
            productListAdapter.submitList(it)
        }
        binding.btnSave.setOnClickListener {
            val name = binding.tieProductName.text.toString()
            val price = binding.tiePrice.text.toString()
            viewModel.insertProduct(
                productName = name,
                productPrice = price,
                productUnit = selectedText,
            )
        }
    }

    private fun setSpinner() {
        spinnerData = resources.getStringArray(R.array.spinner_array)
        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            spinnerData
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = spinnerAdapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                selectedText = spinnerData[position]

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun setupClickListener() {
        productListAdapter.onProductItemClickListener = {


        }
    }

    private fun setupRecyclerView() {
        with(binding.rvProducts) {
            productListAdapter = ProductListAdapter()
            adapter = productListAdapter
            recycledViewPool.setMaxRecycledViews(VIEW_TYPE_PRODUCT, MAX_POOL_SIZE)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}