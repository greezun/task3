package shpp.maslak.task3.ui.fragments.main.myProfile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import shpp.maslak.task3.domain.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun authorizeUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository.authorizeUser(email, password)

            Log.d("MyProfileViewModel", "response: ${response?.user}")
        }
    }
}