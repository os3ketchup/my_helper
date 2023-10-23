package uz.os3ketchup.myhelper.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.os3ketchup.myhelper.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding ?: throw RuntimeException("FragmentLoginBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            launchOtpFragment(binding.etPhone.text.toString())
        }
    }

    private fun launchOtpFragment(phoneNumber: String) {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToOtpFragment(phoneNumber))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}