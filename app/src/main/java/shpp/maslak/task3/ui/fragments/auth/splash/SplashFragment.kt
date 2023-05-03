package shpp.maslak.task3.ui.fragments.auth.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import shpp.maslak.task3.R
import shpp.maslak.task3.data.LoginData
import shpp.maslak.task3.ui.activities.ContactActivity
import shpp.maslak.task3.util.viewModelCreator


class SplashFragment : Fragment() {
    private val viewModel: SplashFragmentViewModel by viewModelCreator {
        SplashFragmentViewModel(LoginData.instance)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeAutoLogin()
    }

    private suspend fun goToNext(isAutologin: Boolean) {
        delay(500)
        if (isAutologin) {
            val intent =
                Intent(requireContext(), ContactActivity::class.java)
            Log.d("myLog", "go to myProfile")
            startActivity(intent)

        } else {
            Log.d("myLog", "signIn")
            val direction = SplashFragmentDirections.actionSplashFragmentToSignUpFragment()
            findNavController().navigate(direction)
        }

    }

    private fun observeAutoLogin() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.isAutoLogin.collectLatest { isAutologin ->
                    goToNext(isAutologin)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("myLog", "SplashFragment on pause")
    }
}