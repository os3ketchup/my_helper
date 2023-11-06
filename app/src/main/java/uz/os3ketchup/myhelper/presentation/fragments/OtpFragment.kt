package uz.os3ketchup.myhelper.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.PhoneAuthProvider
import uz.os3ketchup.myhelper.AuthApp
import uz.os3ketchup.myhelper.R
import uz.os3ketchup.myhelper.databinding.FragmentOtpBinding
import uz.os3ketchup.myhelper.presentation.viewmodels.AuthViewModel
import uz.os3ketchup.myhelper.presentation.viewmodels.Error
import uz.os3ketchup.myhelper.presentation.viewmodels.Progress
import uz.os3ketchup.myhelper.presentation.viewmodels.Result
import uz.os3ketchup.myhelper.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject


class OtpFragment : Fragment() {

    private var _binding: FragmentOtpBinding? = null
    private val binding: FragmentOtpBinding
        get() = _binding ?: throw RuntimeException("FragmentOtpBinding == null")

    private val args by navArgs<OtpFragmentArgs>()

    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    private val component by lazy {
        (requireActivity().application as AuthApp).component
    }


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOtpBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]
        binding.tvOtp.text = args.phoneNumber
        viewModel.sendCode(args.phoneNumber, requireActivity())
        observeViewModel()
        with(binding) {
            btnOtp.setOnClickListener {
                val etCode = etOtp.text.toString()
                if (etCode.isNotEmpty()) {
                    val credential = PhoneAuthProvider.getCredential(viewModel.mVerificationId, etCode)
                    viewModel.signInWithCredit(credential, requireActivity())
                }
            }
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) {

            when (it) {
                is Progress -> {
                    binding.btnOtp.isEnabled = false
                    binding.pbLoading.visibility = View.VISIBLE
                }

                is Result -> {
                    binding.pbLoading.visibility = View.GONE
                    launchUserFragment()
                }

                is Error -> {

                }

            }
        }
    }


    private fun launchUserFragment() {
        findNavController().navigate(OtpFragmentDirections.actionOtpFragmentToUserFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}