package com.example.app1.list

import SeriesPcuCategoryItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.app1.appContext
import com.example.app1.utils.getScreenWidth
import com.example.app1.utils.px2dp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.glide.rememberGlidePainter

@Composable
fun ListPage() {
    val viewModel = viewModel(modelClass = ListViewModel::class.java)
    LaunchedEffect(key1 = "lala"){
        viewModel.getListData()
    }
    Column() {


        if (viewModel.items.size>0){
            LazyColumn(Modifier.fillMaxWidth()){
                items(viewModel.items){ item ->
                InnerList(item = item)
                }
            }
        }

    }

}


@Composable

fun InnerList(item: SeriesPcuCategoryItem) {
    Column {
        Text(text = item.title ?: "")
        if (item.childs.size > 0) {
            LazyRow() {
                items(item.childs){ child ->
                    PcuItemAdapter(item = child, width = getScreenWidth().div(2).plus(30).let {  px2dp(it.toFloat()) } .dp )
                }
            }
        }

    }
}


@Composable
fun PcuItemAdapter(item: SeriesPcuCategoryItem, width:Dp) {
    Column() {
        Image(
            painter = rememberGlidePainter(request = item.thumbnails[0]), contentDescription = "Image",
            modifier = Modifier
                .width(width = width)
                .aspectRatio(1f)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            Text(text = item.title ?: "")
            Text(text = item.price?.toString() ?: "")
            item.json?.optString("more_options_caption")?.let {
                Text(text = it)
            }
        }

    }

}


