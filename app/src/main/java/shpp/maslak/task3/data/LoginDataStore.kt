package shpp.maslak.task3.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import javax.inject.Inject
import javax.inject.Singleton

private const val DATA_STORE_NAME ="dataStore"
private const val ACCESS_TOKEN = "accessToken"
private const val REFRESH_TOKEN = "refreshToken"
private const val USER_ID = "userId"

@Singleton
class LoginDataStore @Inject constructor(@ApplicationContext private  val context:Context ) {
    private val Context.dataStore: DataStore<Preferences>
    by preferencesDataStore(name = DATA_STORE_NAME)

    val accessTokenFlow: Flow<String> = context.dataStore.data.map {
        it[ACCESS_TOKEN_KEY] ?:""
    }

    val refreshTokenFlow: Flow<String> = context.dataStore.data.map {
        it[REFRESH_TOKEN_KEY] ?:""
    }

    val userIdFlow: Flow<Int> = context.dataStore.data.map {
        it[USER_ID_KEY] ?:0
    }




    suspend fun storeTokens( userId:Int, accessToken: String, refreshToken: String) {
        context.dataStore.edit {
            it[ACCESS_TOKEN_KEY] = accessToken
            it[REFRESH_TOKEN_KEY] = refreshToken
            it[USER_ID_KEY] = userId
        }
    }


    companion object{
        val ACCESS_TOKEN_KEY = stringPreferencesKey(ACCESS_TOKEN)
        val REFRESH_TOKEN_KEY = stringPreferencesKey(REFRESH_TOKEN)
        val USER_ID_KEY = intPreferencesKey(USER_ID)
    }
}