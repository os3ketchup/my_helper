package uz.os3ketchup.myhelper.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import uz.os3ketchup.myhelper.AuthApp
import uz.os3ketchup.myhelper.R
import uz.os3ketchup.myhelper.databinding.FragmentChatBinding
import uz.os3ketchup.myhelper.domain.User
import uz.os3ketchup.myhelper.presentation.viewmodels.UserViewModel
import uz.os3ketchup.myhelper.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject


class ChatFragment : Fragment() {


    private var _binding: FragmentChatBinding? = null
    private val binding: FragmentChatBinding
        get() = _binding ?: throw RuntimeException("FragmentChatBinding==null")

    private val args by navArgs<ChatFragmentArgs>()

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}