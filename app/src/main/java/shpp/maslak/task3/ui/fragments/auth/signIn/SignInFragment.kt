package shpp.maslak.task3.ui.fragments.auth.signIn

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import shpp.maslak.task3.databinding.FragmentSignInBinding
import shpp.maslak.task3.ui.base.BaseFragment


@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {

    private val viewModel: SignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitButtonListener()
    }

    private fun submitButtonListener() {
        with(binding) {
            buttonSignIn.setOnClickListener {
                    val email = eMailField.text.toString()
                    val password = passwordField.text.toString()
                    viewModel.createNewUser(email, password)

            }
        }
    }
}