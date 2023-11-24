package uz.os3ketchup.myhelper.presentation.fragments

import android.app.ActionBar.LayoutParams
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.os3ketchup.myhelper.AuthApp
import uz.os3ketchup.myhelper.R
import uz.os3ketchup.myhelper.databinding.FragmentChatBinding
import uz.os3ketchup.myhelper.presentation.viewmodels.ProductViewModel
import uz.os3ketchup.myhelper.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject


class ChatFragment : Fragment() {


    private var _binding: FragmentChatBinding? = null
    private val binding: FragmentChatBinding
        get() = _binding ?: throw RuntimeException("FragmentChatBinding==null")

    private val args by navArgs<ChatFragmentArgs>()

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
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tbChatTitle.text = args.user.name
        viewModel = ViewModelProvider(this, viewModelFactory)[ProductViewModel::class.java]
        binding.btnOrder.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(requireContext())

            val dialogView = layoutInflater.inflate(R.layout.dialog_ordering,null)
            val tvProduct = dialogView.findViewById<TextView>(R.id.tv_product)
            val etAmount = dialogView.findViewById<EditText>(R.id.tie_amount)
            val recyclerView = dialogView.findViewById<RecyclerView>(R.id.rv_bottom_sheet)
            val addButton = dialogView.findViewById<Button>(R.id.btn_save)
            val sendButton = dialogView.findViewById<Button>(R.id.btn_send)
            val spinnerButton = dialogView.findViewById<ImageView>(R.id.spinner_product)
            bottomSheetDialog.setContentView(dialogView)
            showTvDialog(spinnerButton, tvProduct)
            bottomSheetDialog.show()


        }
    }

    private fun showTvDialog(spinnerButton: ImageView, tvProduct: TextView?) {
        spinnerButton.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.dialog_product)
            dialog.window?.setLayout(LayoutParams.MATCH_PARENT,1000)
            val editText = dialog.findViewById<EditText>(R.id.til_product)
            val listView = dialog.findViewById<ListView>(R.id.list_view)
            dialog.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}