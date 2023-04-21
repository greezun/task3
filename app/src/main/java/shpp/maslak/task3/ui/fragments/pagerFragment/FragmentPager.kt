package shpp.maslak.task3.ui.fragments.pagerFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import shpp.maslak.task3.R
import shpp.maslak.task3.databinding.FragmentPagerBinding
import shpp.maslak.task3.ui.base.BaseFragment
import shpp.maslak.task3.ui.fragments.myContact.MyContactsFragment
import shpp.maslak.task3.ui.fragments.myProfile.MyProfileFragment
import shpp.maslak.task3.util.Constants


class FragmentPager : BaseFragment<FragmentPagerBinding>(FragmentPagerBinding::inflate) {

    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = binding.pager
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter
        tabLayout = view.findViewById(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> Constants.MY_CONTACTS
                else -> Constants.MY_PROFILE
            }
        }.attach()


    }


    inner class ViewPagerAdapter(f: FragmentPager) : FragmentStateAdapter(f) {
        override fun getItemCount(): Int = 2


        override fun createFragment(position: Int): Fragment {
            return when (position) {

                0 -> MyContactsFragment()
                else -> MyProfileFragment()
            }
        }
    }

    override fun setObservers() {

    }

    override fun setListeners() {

    }
}