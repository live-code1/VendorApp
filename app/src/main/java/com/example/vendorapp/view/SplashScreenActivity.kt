package com.example.vendorapp.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vendorapp.R
import com.example.vendorapp.common.base.BaseActivity
import com.example.vendorapp.databinding.ActivityMainBinding
import com.example.vendorapp.databinding.ActivitySplashScreenBinding
import com.google.android.material.appbar.AppBarLayout

class SplashScreenActivity : BaseActivity() {
    val TAG = ActivitySplashScreenBinding::class.java.simpleName
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        val view: ConstraintLayout by lazy {
            binding.root
        }
        view.filterTouchesWhenObscured = true
        setContentView(view)

        startMainActivity()
    }

    private fun startMainActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            val newIntent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(newIntent)
        }, 500)

    }


}