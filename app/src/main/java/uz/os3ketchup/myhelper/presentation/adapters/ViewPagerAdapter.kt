package uz.os3ketchup.myhelper.presentation.adapters

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.os3ketchup.myhelper.presentation.fragments.OrderFragment
import uz.os3ketchup.myhelper.presentation.fragments.ProductFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OrderFragment()

            1 -> ProductFragment()

            else -> OrderFragment()
        }
    }

}