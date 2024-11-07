package com.example.vendorapp.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Keep
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

@Keep
abstract class BaseFragments<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment() {

    private var _binding: VB? = null
    val binding: VB
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        if (_binding == null)
            throw IllegalArgumentException("Binding cannot be null")
        return binding.root
    }

    fun showToast(message: String) {
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }
}