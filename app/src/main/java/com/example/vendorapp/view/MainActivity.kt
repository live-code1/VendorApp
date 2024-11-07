package com.example.vendorapp.view

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vendorapp.R
import com.example.vendorapp.common.adapter.CategoryAdapter
import com.example.vendorapp.common.base.BaseActivity
import com.example.vendorapp.common.base.Resource
import com.example.vendorapp.common.data.model.response.Data
import com.example.vendorapp.common.util.CommonUtil
import com.example.vendorapp.databinding.ActivityMainBinding
import com.example.vendorapp.databinding.BottomsheetViewBasketBinding
import com.example.vendorapp.viewmodel.VendorViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    val TAG = MainActivity::class.java.simpleName
    private lateinit var binding: ActivityMainBinding
    private val vendorViewModel: VendorViewModel by viewModels()

    private var data: Data? = null
    private lateinit var categoryAdapter: CategoryAdapter
    private var isScrolling = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view: CoordinatorLayout by lazy {
            binding.root
        }
        view.filterTouchesWhenObscured = true
        setContentView(view)
        initViews()
        apiResponse()
        getProducts()
        viewBasketBottomSheet()

    }

    // initializing views
    private fun initViews() {
        // Add offset change listener to detect the collapsing state
        binding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            if (Math.abs(verticalOffset) == binding.appBarLayout.totalScrollRange) {
                // Fully collapsed, show the title
                binding.txtTitleSmall.text = data?.name
            } else {
                // Expanded, hide the title
                binding.txtTitleSmall.text = ""
            }
        })
    }

    // calling the get products api
    private fun getProducts() {
        if (CommonUtil.isNetworkAvailable(this@MainActivity)) {
            vendorViewModel.getProducts(
                vendorId = 44,
                lang = "en",
                userId = 50,
                isAllowedSampling = null,
                isAllowedRental = null
            )
        } else {
            CommonUtil.showMissingInternetDialog(this@MainActivity)
        }
    }

    // observing for api response
    private fun apiResponse() {

        vendorViewModel.getProductDetails.observe(this@MainActivity) { it ->
            when (it) {
                is Resource.Success -> {
                    val response = it.value

                    if (response.status == 200) {
                        response.data?.let {
                            data = it

                            setDetails()
                            setupTabs()
                            setupRecyclerView()


                        }
                    }
                }

                is Resource.Failure -> {
                    binding.layoutShimmer1.stopShimmer()
                    binding.layoutShimmer2.stopShimmer()
                    binding.layoutShimmer1.isVisible = false
                    binding.layoutShimmer2.isVisible = false
                    binding.txtNoData.isVisible = true
                    Toast.makeText(
                        this@MainActivity,
                        it.errorBody.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    binding.layoutShimmer1.stopShimmer()
                    binding.layoutShimmer2.stopShimmer()
                    binding.layoutShimmer1.isVisible = false
                    binding.layoutShimmer2.isVisible = false
                    binding.txtNoData.isVisible = true
                }
            }
        }
    }

    // Setting vendor details
    private fun setDetails() {
        binding.layoutShimmer1.stopShimmer()
        binding.layoutShimmer2.stopShimmer()
        binding.layoutShimmer1.isVisible = false
        binding.layoutShimmer2.isVisible = false
        binding.detailsLayout.isVisible = true
        binding.categoryRecyclerView.isVisible = true

        Glide.with(this@MainActivity)
            .load(data?.banner)
            .into(binding.imgBanner)

        Glide.with(this@MainActivity)
            .load(data?.logo)
            .into(binding.imgLogo)

        binding.txtName.text = data?.name
        binding.txtDesc.text = data?.description
    }


    // Creating tabs for corresponding category
    private fun setupTabs() {
        data?.categories?.forEach { category ->
            Log.d("TabLayout", "Category name: ${category?.name}")
            val tab = binding.tabLayout.newTab().setText(category?.name)
            binding.tabLayout.addTab(tab)
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    binding.categoryRecyclerView.smoothScrollToPosition(it.position)
                    isScrolling = true
                    tabSelected()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    // Initializing recycler view after api success
    private fun setupRecyclerView() {
        categoryAdapter = CategoryAdapter(this@MainActivity, data?.categories)
        binding.categoryRecyclerView.adapter = categoryAdapter
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(this)


        binding.categoryRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()
                if (firstVisiblePosition != RecyclerView.NO_POSITION && !isScrolling) {
                    binding.tabLayout.setScrollPosition(firstVisiblePosition, 0f, true)
                }
            }
        })
    }

    // View basket bottom sheet
    private fun viewBasketBottomSheet() {
        val dialog = Dialog(this, R.style.DialogThemeSize)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)

        val dialogBinding: BottomsheetViewBasketBinding =
            BottomsheetViewBasketBinding.inflate(layoutInflater)

        dialog.setContentView(dialogBinding.root)

        val window: Window? = dialog.window
        val wlp = window?.attributes
        wlp?.apply {
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.WRAP_CONTENT
            gravity = Gravity.BOTTOM
            windowAnimations = R.style.bottomSheetAnimation
            window.attributes = this
        }

        dialog.show()

    }

    // To prevent revise execution of tab select
    private fun tabSelected() {
        Handler(Looper.getMainLooper()).postDelayed({
            isScrolling = false
        }, 500)
    }

}