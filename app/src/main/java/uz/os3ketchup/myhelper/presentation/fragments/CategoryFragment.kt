package uz.os3ketchup.myhelper.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import uz.os3ketchup.myhelper.R
import uz.os3ketchup.myhelper.databinding.FragmentCategoryBinding
import uz.os3ketchup.myhelper.presentation.adapters.ViewPagerAdapter


class CategoryFragment : Fragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val binding: FragmentCategoryBinding
        get() = _binding ?: throw RuntimeException("FragmentCategoryBinding == null")

    private val args by navArgs<CategoryFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tbChatTitle.text = args.category.name


        val adapter = ViewPagerAdapter(this,args.category) // Use your Fragment reference

        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Buyurtmalar"
                }

                1 -> {
                    tab.text = "Mahsulotlar"
                }

            }
        }.attach()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}