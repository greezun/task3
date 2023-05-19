package shpp.maslak.task3.ui.fragments.main.myProfile

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import shpp.maslak.task3.R
import shpp.maslak.task3.data.source.RetrofitClient
import shpp.maslak.task3.data.source.requests.AuthorizeUserRequest
import shpp.maslak.task3.data.source.requests.NewUserRequest
import shpp.maslak.task3.databinding.FragmentMyProfileBinding
import shpp.maslak.task3.ui.activities.ContactActivity
import shpp.maslak.task3.ui.base.BaseFragment


class MyProfileFragment: BaseFragment<FragmentMyProfileBinding>(FragmentMyProfileBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener() {
        binding.btnMessage.setOnClickListener {
            lifecycleScope.launch{
                val authorizeRequest = NewUserRequest(
                    email = "test5@email",
                    password = "123"
                )

                val authorizeResponse = RetrofitClient.api.createNewUser(authorizeRequest)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as ContactActivity).supportActionBar?.title = getString(R.string.title_my_profile)
    }
}