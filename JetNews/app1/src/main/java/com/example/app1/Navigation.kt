package com.example.app1

import android.os.Bundle
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

val SCRREN_NAME = "screenName"

class NavigationViewModel() : ViewModel() {
   /* var current by savedStateHandle.getMutableStateOf<Screen>(SCRREN_NAME, Screen.ListScreen,
        save = { it.toBundle() },
        restore = {it.toScreen()}
    )*/
    var current by mutableStateOf<Screen>(Screen.ListScreen)

    fun naviTo(screen:Screen){
        current = screen
    }

}

sealed class Screen(val name: String) {
    object ListScreen : Screen("LIST")
    object DetailScreen : Screen("DETAIL")
}

fun Screen.toBundle(): Bundle {
    return bundleOf(SCRREN_NAME to name)
}

fun Bundle.toScreen(): Screen {
    val name = getString(SCRREN_NAME)
    return when (name) {
        "LIST" -> Screen.ListScreen
        "DETAIL" -> Screen.DetailScreen
        else -> Screen.ListScreen
    }


}


fun <T> SavedStateHandle.getMutableStateOf(
    key: String,
    default: T,
    save: (T) -> Bundle,
    restore: (Bundle) -> T
): MutableState<T> {
    val bundle: Bundle? = get(key)
    val initial = if (bundle == null) {
        default
    } else {
        restore.invoke(bundle)
    }
    val state = mutableStateOf(initial)
    setSavedStateProvider(key) {
        save(state.value)
    }
    return mutableStateOf(initial)


}
