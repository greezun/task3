package shpp.maslak.task3.ui.fragments.main.pagerFragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import shpp.maslak.task3.databinding.FragmentPagerBinding


import shpp.maslak.task3.ui.base.BaseFragment
import shpp.maslak.task3.ui.fragments.main.myContacts.MyContactsFragment
import shpp.maslak.task3.ui.fragments.main.myProfile.MyProfileFragment
import shpp.maslak.task3.util.Constants


@AndroidEntryPoint
class FragmentPager
    : BaseFragment<FragmentPagerBinding>(FragmentPagerBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = binding.pager
        val adapter = ViewPagerAdapter(this)
        val tabLayout = binding.tabLayout
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> Constants.MY_PROFILE
                else -> Constants.MY_CONTACTS
            }
        }.attach()

        Log.d("myLog", "pager started")

    }


    inner class ViewPagerAdapter(f: FragmentPager) : FragmentStateAdapter(f) {
        override fun getItemCount(): Int = 2


        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> MyProfileFragment()
                else -> MyContactsFragment()
            }
        }
    }

}