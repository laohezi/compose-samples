package com.example.app1.list

import SeriesPcuCategoryItem
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.app1.NavigationViewModel
import com.example.app1.Screen
import com.example.app1.utils.getScreenWidth
import com.example.app1.utils.px2dp
import kotlin.math.log

@Composable
fun ListPage(

) {
    val viewModel = viewModel(modelClass = ListViewModel::class.java)

    LaunchedEffect(key1 = "lala") {
        viewModel.getListData()
    }
    Column() {
        if (viewModel.items.size > 0) {
            LazyColumn(Modifier.fillMaxWidth()) {
                viewModel.banner?.let {
                    item {
                       ImageWithLoading(url = it as String, imageViewModifier =
                       Modifier.fillMaxWidth().aspectRatio(2.0f)
                       )
                    }
                }
                items(viewModel.items) { item ->
                    InnerList(item = item)
                }
            }
        }

    }

}


@Composable

fun InnerList(item: SeriesPcuCategoryItem) {
    Column(modifier = Modifier.padding(top = 12.dp)) {
        Text(text = item.title ?: "")
        if (item.childs.size > 0) {
            LazyRow() {
                items(item.childs) { child ->
                    PcuItemAdapter(
                        item = child,
                        width = getScreenWidth().div(2).plus(30).let { px2dp(it.toFloat()) }.dp
                    )
                }
            }
        }

    }
}


@Composable
fun PcuItemAdapter(item: SeriesPcuCategoryItem, width: Dp, onClick: (() -> Unit)? = null) {
    val navigationViewModel = viewModel(modelClass = NavigationViewModel::class.java)
    Column(Modifier.clickable(enabled = item.isSale()) {
        navigationViewModel.naviTo(Screen.DetailScreen)
    }.fillMaxWidth()) {

       item.thumbnails.firstOrNull()?.let {
           ImageWithLoading(url = it, imageViewModifier = Modifier
               .fillMaxWidth()
               .aspectRatio(1.0f) )
       }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = item.title ?: "")
            Text(text = item.price?.toString() ?: "")
            item.json?.optString("more_options_caption")?.let {
                Text(text = it)
            }
        }

    }

}


typealias  NT = (Screen) -> Unit

@Composable
fun ImageWithLoading(url:String, imageViewModifier: Modifier){
    val painter = rememberImagePainter(url)
    Box {
        Image(
            painter = painter,
            contentDescription = "Image",
            modifier =imageViewModifier,
            contentScale = ContentScale.Crop
        )
        when(painter.state){
            ImagePainter.State.Empty -> {

            }
            is ImagePainter.State.Loading ->{
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
            is ImagePainter.State.Error -> {
                println("lalallacuole")
            }
        }

    }

}


