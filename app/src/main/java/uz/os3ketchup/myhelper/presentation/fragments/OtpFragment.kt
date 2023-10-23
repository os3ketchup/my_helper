package uz.os3ketchup.myhelper.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.os3ketchup.myhelper.R
import uz.os3ketchup.myhelper.databinding.FragmentOtpBinding


class OtpFragment : Fragment() {

    private var _binding: FragmentOtpBinding? = null
    private val binding: FragmentOtpBinding
        get() = _binding ?: throw RuntimeException("FragmentOtpBinding == null")

    private val args by navArgs<OtpFragmentArgs>()

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
        binding.etOtp.setText(args.phoneNumber)
        launchUserFragment()
    }

    private fun launchUserFragment() {
        findNavController().navigate(R.id.action_otpFragment_to_userFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}