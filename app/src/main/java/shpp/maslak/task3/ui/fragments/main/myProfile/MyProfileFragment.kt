package shpp.maslak.task3.ui.fragments.main.myProfile

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import shpp.maslak.task3.R
import shpp.maslak.task3.data.model.User
import shpp.maslak.task3.databinding.FragmentMyProfileBinding
import shpp.maslak.task3.ui.activities.ContactActivity
import shpp.maslak.task3.ui.base.BaseFragment

@AndroidEntryPoint
class MyProfileFragment: BaseFragment<FragmentMyProfileBinding>(FragmentMyProfileBinding::inflate) {

    private val viewModel: MyProfileViewModel by viewModels()






    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = requireActivity().intent
        val key = "LOGIN_DATA"

        val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(key, User::class.java)
        } else {
            intent.getParcelableExtra(key)
        }




        Log.d("userLog", "user in my profile $user")
//        setListener()
    }

//    private fun setListener() {
//        binding.btnMessage.setOnClickListener {
//            lifecycleScope.launch{
////                val authorizeRequest = NewUserRequest(
////                    email = "test5@email",
////                    password = "123"
////                )
//
//                viewModel.authorizeUser(
//                    email = "test11@email",
//                    password = "123"
//                )
//
////                val authorizeResponse = RetrofitClient.api.createNewUser(authorizeRequest)
//            }
//        }
//    }

    override fun onResume() {
        super.onResume()
        (activity as ContactActivity).supportActionBar?.title = getString(R.string.title_my_profile)
    }
}