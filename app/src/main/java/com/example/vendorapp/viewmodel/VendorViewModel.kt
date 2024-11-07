package com.example.vendorapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vendorapp.common.base.Resource
import com.example.vendorapp.common.data.model.response.CommonResponse
import com.example.vendorapp.common.data.remote.repository.VendorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VendorViewModel @Inject constructor(private val vendorRepository: VendorRepository) :
    ViewModel() {

    /*
    * For getting user profile details
    */
    private val _getProducts: MutableLiveData<Resource<CommonResponse>> =
        MutableLiveData()
    val getProductDetails: LiveData<Resource<CommonResponse>>
        get() = _getProducts

    fun getProducts(
        vendorId: Int?,
        lang: String?,
        userId: Int?,
        isAllowedSampling: Boolean?,
        isAllowedRental: Boolean?
    ) {
        viewModelScope.launch {
            _getProducts.value = vendorRepository.getUserDetails(
                vendorId,
                lang,
                userId,
                isAllowedSampling,
                isAllowedRental
            )
        }
    }


}