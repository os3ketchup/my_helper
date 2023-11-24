package uz.os3ketchup.myhelper.presentation.adapters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.os3ketchup.myhelper.domain.Category
import uz.os3ketchup.myhelper.presentation.fragments.CategoryFragmentDirections
import uz.os3ketchup.myhelper.presentation.fragments.OrderFragment
import uz.os3ketchup.myhelper.presentation.fragments.ProductFragment

class ViewPagerAdapter(val fragment: Fragment,val category:Category) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                val orderFragment = OrderFragment()
                val bundle = Bundle()
                bundle.putParcelable("categoryName", category)
                orderFragment.arguments = bundle
                orderFragment
            }
            1 ->{
                val orderFragment = ProductFragment()
                val bundle = Bundle()
                bundle.putParcelable("categoryName", category)
                orderFragment.arguments = bundle
                orderFragment
            }


            else -> OrderFragment()
        }
    }

}