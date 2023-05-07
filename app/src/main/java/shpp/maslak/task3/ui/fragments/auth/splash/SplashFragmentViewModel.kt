package shpp.maslak.task3.ui.fragments.auth.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import shpp.maslak.task3.data.LoginData
import javax.inject.Inject

@HiltViewModel
class SplashFragmentViewModel @Inject constructor(loginData: LoginData): ViewModel() {

    private val _isAutoLogin = MutableStateFlow(false)
    val isAutoLogin: StateFlow<Boolean> = _isAutoLogin

    init {
            viewModelScope
                .launch(Dispatchers.IO) {
                    loginData.autoLoginFlow.collect {
                        _isAutoLogin.value = it
                        Log.d("myLog", "isAutoLogin in splashViewModel $it")

                }
        }
    }


}