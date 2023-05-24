package shpp.maslak.task3.ui.fragments.auth.signUp.ext

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs

import kotlinx.coroutines.launch
import shpp.maslak.task3.databinding.FragmentSignUpExtBinding
import shpp.maslak.task3.ui.activities.ContactActivity
import shpp.maslak.task3.ui.base.BaseFragment


class SignUpExtFragment :
    BaseFragment<FragmentSignUpExtBinding>(FragmentSignUpExtBinding::inflate) {

    private val viewModel by viewModels<SignUpExtViewModel>()
    private val args by navArgs<SignUpExtFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentUser = args.user
        Log.d("singUp", "user $currentUser")
        viewModel.initUser(currentUser)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
    }

    private fun setListeners() {
        userNameFocusListener()
        userPhoneFocusListener()
        forwardButtonListener()

    }



    private fun setObservers(){
        observeUserName()
        observeUserPhone()

    }

    private fun observeUserName(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.currentUserName.collect{
                    binding.textInputEditTextUerName.setText(it)
                }
            }
        }
    }

    private fun observeUserPhone(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.currentUserPhone.collect{
                    binding.textInputEditTextPhone.setText(it)
                }
            }
        }
    }

    private fun userNameFocusListener() {
        with(binding) {
            textInputLayoutUserName.setOnFocusChangeListener { _, focused ->
                if (!focused) {
                    viewModel.setUsername(textInputEditTextUerName.text.toString())

                }
            }
        }
    }

    private fun userPhoneFocusListener() {
        with(binding) {
            textInputLayoutPhone.setOnFocusChangeListener { _, focused ->
                if (!focused) {
                    viewModel.senPhoneNumber(textInputEditTextPhone.text.toString())

                }
            }
        }
    }

    private fun forwardButtonListener() {
        binding.buttonForward.setOnClickListener {


            val intent =
                Intent(requireContext(), ContactActivity::class.java)
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.currentUser.collect {
                        if (it != null) {
                            intent.putExtra("LOGIN_DATA", it)
                            startActivity(intent)

                        }
                    }
                }
            }
        }
    }

}
