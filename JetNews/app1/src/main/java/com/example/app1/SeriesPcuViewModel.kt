package com.example.app1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SeriesPcuViewModel : ViewModel() {
    val detailGroup = MutableLiveData<SeriesPcuCategoryItem>()
}