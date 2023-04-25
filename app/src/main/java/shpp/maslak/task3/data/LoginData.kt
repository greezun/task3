package shpp.maslak.task3.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private const val USER_LOGIN = "USER_LOGIN"
private const val USER_PASSWORD ="USER_PASSWORD"
private const val AUTOLOGIN ="AUTOLOGIN"
private const val DATA_STORE_NAME = "userLogin"

class LoginData(private val context: Context) {


    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

    val eMailFlow: Flow<String> = context.dataStore.data.map {
        it[USER_LOGIN_KEY] ?: ""
    }

    val passwordFlow: Flow<String> = context.dataStore.data.map {
        it[USER_PASSWORD_KEY] ?: ""
    }

    val autoLoginFlow: Flow<Boolean> = context.dataStore.data.map {
        it[AUTOLOGIN_KEY] ?: false
    }

    suspend fun storeLoginData(login: String, password: String, isAutologin: Boolean) {
        context.dataStore.edit {
            it[USER_LOGIN_KEY] = login
            it[USER_PASSWORD_KEY] = password
            it[AUTOLOGIN_KEY] = isAutologin
        }

    }


    companion object {
        val USER_LOGIN_KEY = stringPreferencesKey(USER_LOGIN)
        val USER_PASSWORD_KEY = stringPreferencesKey(USER_PASSWORD)
        val AUTOLOGIN_KEY = booleanPreferencesKey(AUTOLOGIN)
    }








}