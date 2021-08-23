package com.example.app1.detail

import android.os.Bundle
import android.renderscript.Sampler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.app1.FBYJsonParser
import com.example.app1.LiveBannerView
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.glide.rememberGlidePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import jp.wasabeef.composable.glide.GlideImage
import org.json.JSONObject


@ExperimentalPagerApi
@Composable
fun DetailPage( viewModel: SeriesPcuDetailViewModel) {
    viewModel.initData()
    Column() {
        LiveBannerView(viewModel = viewModel)
        SampleLooper(viewModel = viewModel)
    }
}
@ExperimentalPagerApi
@Composable
fun DetailPage(banner:Any?,samples:List<SeriesPcuDetailSampleItem>?){

}

@ExperimentalPagerApi
@Composable
fun SampleLooper(viewModel:SeriesPcuDetailViewModel) {
    val datas = viewModel.pageData.samples.observeAsState()
    val pagerState = rememberPagerState(
        pageCount = datas.value!!.size
    )
    Column() {
        HorizontalPager(state = pagerState) {
            datas.value!!.forEach { data ->
                Image(
                    painter = rememberGlidePainter(request = data.sampleUrl),
                    contentDescription = "lal",
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .aspectRatio(1.0f)
                )
            }
        }

        HorizontalPagerIndicator(pagerState = pagerState)
    }

}
@Preview
@Composable
fun ImageViewTest(){
    Image(painter = rememberImagePainter(
       "https://img0.baidu.com/it/u=103721101,4076571305&fm=26&fmt=auto&gp=0.jpg"
    ), contentDescription ="" )
}

@Preview
@Composable
fun TextViewTest(){
    Text(text = "lalalla")
}































