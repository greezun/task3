package shpp.maslak.task3.ui.fragments.main.myProfile


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import shpp.maslak.task3.data.LoginDataStore
import shpp.maslak.task3.data.model.User
import shpp.maslak.task3.domain.repository.UserRepository

import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
private val dataStore: LoginDataStore,
    private val userRepository: UserRepository

) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    init {
        viewModelScope.launch {
            val  accessToken = async {  getAccessToken()}.await()
            val  userId = async { getUserId() }.await()
            getUserFromServer(userId, accessToken)
        }

    }


     private suspend fun getAccessToken(): String{
        return dataStore.accessTokenFlow.first()
    }

    private suspend fun getUserId():Int{
        return dataStore.userIdFlow.first()
    }

    private suspend fun getUserFromServer(userId:Int, accessToken:String ){
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository.getUser(userId, accessToken)
            if (response != null) {
                val user = response.user
                _user.value = user


            }
        }

    }


}