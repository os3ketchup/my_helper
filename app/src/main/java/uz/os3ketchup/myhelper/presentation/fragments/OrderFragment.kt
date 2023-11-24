package uz.os3ketchup.myhelper.presentation.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout.LayoutParams
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.os3ketchup.myhelper.AuthApp
import uz.os3ketchup.myhelper.R
import uz.os3ketchup.myhelper.databinding.FragmentOrderBinding
import uz.os3ketchup.myhelper.domain.Category
import uz.os3ketchup.myhelper.domain.Product
import uz.os3ketchup.myhelper.presentation.adapters.OrderListAdapter
import uz.os3ketchup.myhelper.presentation.adapters.OrderListAdapter.Companion.MAX_POOL_SIZE
import uz.os3ketchup.myhelper.presentation.adapters.OrderListAdapter.Companion.VIEW_TYPE_ORDERS
import uz.os3ketchup.myhelper.presentation.adapters.PickingListAdapter
import uz.os3ketchup.myhelper.presentation.adapters.ProductListAdapter
import uz.os3ketchup.myhelper.presentation.viewmodels.ProductViewModel
import uz.os3ketchup.myhelper.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject


class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding: FragmentOrderBinding
        get() = _binding ?: throw RuntimeException("FragmentOrderBinding == null")

    private lateinit var customProductList: MutableSet<Product>
    private lateinit var uniqueProductList: List<Product>
    private lateinit var pickingListAdapter: PickingListAdapter
    private lateinit var orderListAdapter: OrderListAdapter
    private var productPosition = 0

    val category by lazy {
        arguments?.getParcelable<Category>("categoryName")
    }

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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[ProductViewModel::class.java]
        viewModel.orderList.observe(viewLifecycleOwner) {
            orderListAdapter.submitList(it)
        }
        setupRecyclerView()
        setupClickListener()

        binding.btnOrder.setOnClickListener {
            showBottomSheet()
        }
    }

    private fun setupClickListener() {

    }

    private fun setupRecyclerView() {
        with(binding.rvOrders) {
            orderListAdapter = OrderListAdapter()
            adapter = orderListAdapter
            recycledViewPool.setMaxRecycledViews(VIEW_TYPE_ORDERS, MAX_POOL_SIZE)
        }
    }

    @SuppressLint("MissingInflatedId", "InflateParams")
    private fun showBottomSheet() {
        customProductList = mutableSetOf()

        val bottomSheetDialog =
            BottomSheetDialog(requireContext()) // 'this' refers to your activity or context

        // Inflate the layout for the bottom sheet
        val view = layoutInflater.inflate(R.layout.dialog_ordering, null)
        val tvProduct = view.findViewById<TextView>(R.id.tv_product)
        val etAmount = view.findViewById<EditText>(R.id.tie_amount)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_bottom_sheet)
        val addButton = view.findViewById<Button>(R.id.btn_save)
        val sendButton = view.findViewById<Button>(R.id.btn_send)
        val spinnerButton = view.findViewById<ImageView>(R.id.spinner_product)
        view.findViewById<TextView>(R.id.tv_category_name).visibility = View.GONE
        view.findViewById<ImageView>(R.id.spinner_category).visibility = View.GONE
        setupAddButton(addButton, recyclerView, tvProduct, etAmount)
        sendButton.setOnClickListener {
            viewModel.insertOrder(uniqueProductList, "2x", "x2")
        }
        bottomSheetDialog.setContentView(view)
        showTvDialog(spinnerButton, tvProduct)
        bottomSheetDialog.show()
    }

    private fun setupAddButton(
        addButton: Button,
        recyclerView: RecyclerView,
        tvProduct: TextView,
        etAmount: EditText
    ) {
        addButton.setOnClickListener {
            setupPickerRecyclerview(recyclerView)
            viewModel.productList.observe(viewLifecycleOwner) {
                val id = it[productPosition].id
                val productName = tvProduct.text.toString()
                val amountProduct = etAmount.text.toString()

                val price = it.find { product->
                    product.name == productName
                }?.price?:""

                val product =
                    Product(name = productName, amount = amountProduct, id = id, price = price)
                customProductList.add(product)
            }
            uniqueProductList = customProductList
                .reversed()
                .groupBy { it.name }
                .values
                .map { it.first() }
                .reversed()

            pickingListAdapter.submitList(uniqueProductList)

            Log.d(TRY, "showBottomSheet: $customProductList")
        }
    }

    private fun showTvDialog(buttonDropDown: ImageView, tvProduct: TextView) {
        buttonDropDown.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.dialog_product)
            dialog.window?.setLayout(LayoutParams.MATCH_PARENT, 1000)
            val editText = dialog.findViewById<EditText>(R.id.til_product)
            val listView = dialog.findViewById<ListView>(R.id.list_view)
            dialog.show()
            viewModel.productList.observe(viewLifecycleOwner) { productList ->
                val productNameList = productList.filter {
                    it.categoryName == category?.name
                }.map { product ->
                    product.name
                }

                val listAdapter = ArrayAdapter(
                    requireContext(),
                    com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                    productNameList
                )
                listView.adapter = listAdapter
                editText.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?, start: Int, count: Int, after: Int
                    ) {

                    }

                    override fun onTextChanged(
                        s: CharSequence?, start: Int, before: Int, count: Int
                    ) {
                        listAdapter.filter.filter(s)
                    }

                    override fun afterTextChanged(s: Editable?) {

                    }
                })
                listView.setOnItemClickListener { _, _, position, _ ->
                    tvProduct.text = listAdapter.getItem(position)
                    productPosition = position
                    dialog.dismiss()
                }
            }
        }
    }

    private fun setupPickerRecyclerview(recyclerView: RecyclerView) {
        pickingListAdapter = PickingListAdapter()
        recyclerView.adapter = pickingListAdapter
        recyclerView.recycledViewPool.setMaxRecycledViews(
            ProductListAdapter.VIEW_TYPE_PRODUCT, ProductListAdapter.MAX_POOL_SIZE
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TRY = "LIST TRY"
    }

}