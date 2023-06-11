package shpp.maslak.task3.ui.fragments.main.myProfile

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import shpp.maslak.task3.R
import shpp.maslak.task3.data.model.User
import shpp.maslak.task3.databinding.FragmentMyProfileBinding
import shpp.maslak.task3.ui.activities.ContactActivity
import shpp.maslak.task3.ui.activities.ContactActivityArgs
import shpp.maslak.task3.ui.base.BaseFragment

@AndroidEntryPoint
class MyProfileFragment: BaseFragment<FragmentMyProfileBinding>(FragmentMyProfileBinding::inflate) {

    private val viewModel: MyProfileViewModel by viewModels()
    private val args by navArgs<ContactActivityArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userName.text= args.user.email

//        observeUserData()


    }

//    private fun observeUserData() {
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED){
//                viewModel.user.collect{
//                    binding.userName.text = it.email
//                }
//            }
//
//        }
//
//    }


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