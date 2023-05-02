package shpp.maslak.task3.ui.fragments.auth.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import shpp.maslak.task3.App
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

    private fun goToNext(isAutologin: Boolean) {
        Log.d("myLog", "autologin $isAutologin")
        if (isAutologin) {
            val intent =
                Intent(App.instance.applicationContext, ContactActivity::class.java)
            startActivity(intent)
        } else {
            val direction = SplashFragmentDirections.actionSplashFragmentToSignUpFragment()
            findNavController().navigate(direction)
        }
    }

    private fun observeAutoLogin() {
        lifecycleScope.launchWhenCreated {
            viewModel.isAutoLogin.collect { isAutologin ->
                goToNext(isAutologin)
            }
        }
    }


}