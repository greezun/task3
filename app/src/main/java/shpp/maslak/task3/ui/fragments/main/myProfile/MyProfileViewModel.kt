package shpp.maslak.task3.ui.fragments.main.myProfile

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import shpp.maslak.task3.data.LoginDataStore
import shpp.maslak.task3.data.model.User
import shpp.maslak.task3.ui.activities.ContactActivityArgs
import shpp.maslak.task3.ui.fragments.auth.signUp.ext.SignUpExtFragmentArgs
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(


) : ViewModel() {



    private val _user = MutableStateFlow(null)
    val user = _user.asStateFlow()




}