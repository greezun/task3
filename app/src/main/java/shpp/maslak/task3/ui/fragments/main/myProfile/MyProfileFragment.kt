package shpp.maslak.task3.ui.fragments.main.myProfile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import shpp.maslak.task3.R
import shpp.maslak.task3.databinding.FragmentMyProfileBinding
import shpp.maslak.task3.ui.activities.ContactActivity
import shpp.maslak.task3.ui.base.BaseFragment

@AndroidEntryPoint
class MyProfileFragment: BaseFragment<FragmentMyProfileBinding>(FragmentMyProfileBinding::inflate) {

    val viewModel: MyProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener() {
        binding.btnMessage.setOnClickListener {
            lifecycleScope.launch{
//                val authorizeRequest = NewUserRequest(
//                    email = "test5@email",
//                    password = "123"
//                )

                viewModel.authorizeUser(
                    email = "test10@email",
                    password = "123"
                )

//                val authorizeResponse = RetrofitClient.api.createNewUser(authorizeRequest)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as ContactActivity).supportActionBar?.title = getString(R.string.title_my_profile)
    }
}