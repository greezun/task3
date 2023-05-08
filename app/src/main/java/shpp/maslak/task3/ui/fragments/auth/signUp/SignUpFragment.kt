package shpp.maslak.task3.ui.fragments.auth.signUp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import shpp.maslak.task3.databinding.FragmentSignUpBinding
import shpp.maslak.task3.ui.activities.ContactActivity
import shpp.maslak.task3.ui.base.BaseFragment

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {

    private val viewModel  by viewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
    }

    fun setObservers() {
        observeEmailErrorMessage()
        observePasswordErrorMessage()
        observeUserLoginData()
    }

    private fun observeUserLoginData() {
        with(binding) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.userEMail.collect {
                        eMailField.setText(it)
                    }
                }
            }

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.userPassword.collect {
                        passwordField.setText(it)
                    }
                }
            }
        }
    }

     fun setListeners() {
        emailFocusListener()
        passwordFocusListener()
        submitButtonListener()
    }

    private fun observePasswordErrorMessage() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.passwordErrorMessage.collect {
                    binding.passwordContainer.helperText = it
                }
            }
        }
    }

    private fun observeEmailErrorMessage() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eMailErrorMessage.collect {
                    binding.eMailContainer.helperText = it
                }
            }
        }
    }


    private fun passwordFocusListener() {
        with(binding) {
            passwordField.setOnFocusChangeListener { _, focused ->
                if (!focused) {
                    viewModel.validPassword(passwordField.text.toString())
                }
            }
        }
    }

    private fun emailFocusListener() {
        with(binding) {
            eMailField.setOnFocusChangeListener { _, focused ->
                if (!focused) {
                    viewModel.validEMail(eMailField.text.toString())

                }
            }
        }
    }

    private fun clearFocuses() {
        binding.eMailField.clearFocus()
        binding.passwordField.clearFocus()
    }


    private fun isFieldsValid(): Boolean {
        with(binding) {
            viewModel.validEMail(eMailField.text.toString())
            viewModel.validPassword(passwordField.text.toString())
            return passwordContainer.helperText == null
                    && eMailContainer.helperText == null
        }
    }


    private fun submitButtonListener() {
        with(binding) {
            buttonSignUp.setOnClickListener {
                clearFocuses()
                if (isFieldsValid()) {
                    val eMail = eMailField.text.toString()
                    val password = passwordField.text.toString()
                    val isCheckedCheckBox = checkBox.isChecked
                    viewModel.saveLoginData(eMail, password, isCheckedCheckBox)
                    val intent =
                        Intent(requireContext(), ContactActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

}