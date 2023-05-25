package shpp.maslak.task3.ui.fragments.auth.signUp.ext

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs

import kotlinx.coroutines.launch
import shpp.maslak.task3.databinding.FragmentSignUpExtBinding
import shpp.maslak.task3.ui.activities.ContactActivity
import shpp.maslak.task3.ui.base.BaseFragment
import shpp.maslak.task3.util.setContactPhoto


class SignUpExtFragment :
    BaseFragment<FragmentSignUpExtBinding>(FragmentSignUpExtBinding::inflate) {

    private val viewModel by viewModels<SignUpExtViewModel>()
//    private val args by navArgs<SignUpExtFragmentArgs>()

    private val requestImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            Log.d("myUritag", "uri in fragment" )
            Log.d("myUritag", "uri in fragment $uri")
             viewModel.setPhoto(uri!!)
        }

    private var avatarObserver: Observer<Any>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val currentUser = args.user
//        Log.d("singUp", "user $currentUser")
//        viewModel.initUser(currentUser)
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
        addPhotoListener()

    }

    private fun setAvatarObserver() = with(viewModel.currentUserPhotoUri) {
        avatarObserver = Observer<Any> {
            value?.let {
                binding.imageViewAddPhoto.setContactPhoto(value!!)
            }
        }
    }

    private fun addPhotoListener() {
        binding.imageViewAddPhoto.setOnClickListener {
            addPhotoFromGallery()
        }
    }

    private fun addPhotoFromGallery() {
        Log.d("myUritag", "start")
        requestImageLauncher.launch("image/*")
    }




    private fun setObservers(){
        observeUserName()
        observeUserPhone()
        observeUserPhoto()
//        setAvatarObserver()

    }

    private fun observeUserPhoto() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.currentUserPhotoUri.collect{
                   binding.imageViewAvatar.setContactPhoto(it)
                }
            }
        }
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

    override fun onPause() {
        super.onPause()
        Log.d("myUritag", "pause" )

    }

    override fun onResume() {
        super.onResume()
        Log.d("myUritag", "resume" )
    }

}
