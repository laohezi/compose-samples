package com.example.app1

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app1.detail.DetailPage
import com.example.app1.detail.SeriesPcuDetailViewModel
import com.example.app1.list.ListPage
import com.google.accompanist.pager.ExperimentalPagerApi


class MainActivity : AppCompatActivity() {

    val   viewModel  by viewModels<SeriesPcuDetailViewModel>()
    val   flowViewModel  by viewModels<SeriesPcuViewModel>()

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.flowViewModel = flowViewModel

        setContent {
          ListPage()
        }
    }
}





