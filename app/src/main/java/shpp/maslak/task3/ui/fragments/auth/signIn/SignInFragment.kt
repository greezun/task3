package shpp.maslak.task3.ui.fragments.auth.signIn

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import shpp.maslak.task3.data.model.User
import shpp.maslak.task3.databinding.FragmentSignInBinding
import shpp.maslak.task3.ui.base.BaseFragment
import shpp.maslak.task3.ui.navigator.NavigationEvents


@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {

    private val viewModel: SignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitButtonListener()
        eventListener()
    }

    private fun eventListener() {
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.events.collect { event ->
                        when(event){
                            is NavigationEvents.ToNextFragment -> goToNext(event.args)
                            is NavigationEvents.ShowToast -> showToast()
                            else -> {}
                        }

                    }
                }

        }
    }

    private fun showToast() {
        TODO("Not yet implemented")
    }

    private fun goToNext(args:Any?) {

        val direction = SignInFragmentDirections.actionSignInFragmentToContactActivity(args as User)
        findNavController().navigate(direction)
    }

    private fun submitButtonListener() {
        binding.buttonSignIn.setOnClickListener {
            authoriseUser()

        }
    }

    private fun authoriseUser() {
        with(binding) {
            val email = "test5@email"
            val password = "123"
            viewModel.authoriseUser(email, password)
        }
    }
}