package com.example.vendorapp.common.base

import androidx.annotation.Keep
import androidx.lifecycle.ViewModel

@Keep
abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {

}