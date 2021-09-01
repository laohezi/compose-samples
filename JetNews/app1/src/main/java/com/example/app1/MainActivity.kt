package com.example.app1

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app1.detail.DetailPage
import com.example.app1.detail.SeriesPcuDetailViewModel
import com.example.app1.list.ListPage
import com.google.accompanist.pager.ExperimentalPagerApi


class MainActivity : AppCompatActivity() {


    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent()
        }
    }
}

@ExperimentalPagerApi
@Composable
fun AppContent() {
    val navigationViewModel = viewModel(modelClass = NavigationViewModel::class.java)
    Crossfade(targetState = navigationViewModel.current,
    animationSpec = spring() ) { screen ->
        when (screen) {
            Screen.DetailScreen -> DetailPage()
            Screen.ListScreen -> ListPage()
        }

    }

}





