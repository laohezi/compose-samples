package com.example.app1.detail

import android.os.Bundle
import android.renderscript.Sampler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import com.example.app1.FBYJsonParser
import com.example.app1.LiveBannerView
import jp.wasabeef.composable.glide.GlideImage


@Composable
fun DetailPage(){
    Column() {
       LiveBannerView(json = FBYJsonParser.getJSONFormAssets("banner.json")!!)
    }

}






























