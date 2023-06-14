package shpp.maslak.task3.ui.fragments.main.myProfile


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import shpp.maslak.task3.R
import shpp.maslak.task3.databinding.FragmentMyProfileBinding
import shpp.maslak.task3.ui.activities.ContactActivity

import shpp.maslak.task3.ui.base.BaseFragment

@AndroidEntryPoint
class MyProfileFragment: BaseFragment<FragmentMyProfileBinding>(FragmentMyProfileBinding::inflate) {

    private val viewModel: MyProfileViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUserData()
    }

    private fun observeUserData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.user.collect{
                    binding.userName.text = it?.email
                }
            }

        }

    }




    override fun onResume() {
        super.onResume()
        (activity as ContactActivity).supportActionBar?.title = getString(R.string.title_my_profile)
    }
}