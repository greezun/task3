package shpp.maslak.task3.ui.navigator

import android.app.usage.UsageEvents.Event
import androidx.navigation.NavArgs
import shpp.maslak.task3.data.model.User

sealed class NavigationEvents {

    object ToNextFragment:NavigationEvents()
    object ShowToast:NavigationEvents()
}