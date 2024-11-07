package com.example.vendorapp.common.base

import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.Keep
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.example.vendorapp.R

@Keep
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        // For Logging all the keys of paperDB
       // Log.d(this.javaClass.simpleName, "itemClickListener: All_Keys -> ${Paper.book().allKeys}")
    }
}