package com.hspl.imgur_app.activity

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.OnClickListener
import android.view.View.VISIBLE
import android.view.Window
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hspl.imgur_app.R
import com.hspl.imgur_app.adapter.DashboardAdapter
import com.hspl.imgur_app.core.ParentActivity
import com.hspl.imgur_app.databinding.ActivityDashboardBinding
import com.hspl.imgur_app.databinding.LayoutDialogExitBinding
import com.hspl.imgur_app.utils.AppUtils
import com.hspl.imgur_app.viewModel.DashboardViewModel

class DashboardActivity : ParentActivity(), OnClickListener {

    // Initialising variables
    private lateinit var mContext: Context
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var viewModel: DashboardViewModel

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var dashboardAdapter: DashboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mContext = this

        // Adding click listeners
        binding.layoutToolbar.imgExit.setOnClickListener(this)
        binding.imgSearch.setOnClickListener(this)
        binding.imgListView.setOnClickListener(this)
        binding.txtListView.setOnClickListener(this)
        binding.imgGridView.setOnClickListener(this)
        binding.txtGridView.setOnClickListener(this)

        binding.cardView.visibility = GONE

        // showing loading dialog to fetch data
        showLoadingDialog(mContext)

        // setting recycler view
        prepareRecyclerView()

        // calling view model
        viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]

        // Check internet is connected or not
        if (isInternetConnected(mContext))
        // Call API to get the data
            viewModel.getDataList()
        else {
            // Show no internet dialog
            showDialog(getString(R.string.internet), getString(R.string.check_your_internet_connection))
        }

        // Live data observer if data changes then this method will get called
        viewModel.dashboardLiveData.observe(this) { list ->
            dashboardAdapter.setTicketList(list)
        }

        // If any issue then this method will get called
        viewModel.messages.observe(this) { data ->

            showToast(mContext, data)

            dismissLoadingDialog()
        }

        // Edit Text change listener to get the input text and call search method
        binding.edtSearch.addTextChangedListener {
            viewModel.searchData(binding.edtSearch.text.toString())
        }

        // To handle back navigation
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showDialog(getString(R.string.exit), getString(R.string.do_you_really_want_to_n_exit_form_app))
            }
        })
    }

    private fun prepareRecyclerView() {
        // Setting grid layout to 1 for list view
        gridLayoutManager = GridLayoutManager(mContext, 1)

        // Initialising the Adapter view
        dashboardAdapter = DashboardAdapter(gridLayoutManager)

        // Setting layout manager and adapter to recycler view
        binding.recyclerView.apply {
            layoutManager = gridLayoutManager
            adapter = dashboardAdapter
        }

        // adding scroll listener to show and hide the bottom card view
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // if the recycler view is scrolled above hide the bottom cardView
                if (dy > 10) {
                    binding.cardView.visibility = GONE
                }

                // if the recycler view is scrolled above show the bottom cardView
                if (dy < -10) {
                    binding.cardView.visibility = VISIBLE
                }

                // of the recycler view is at the first
                // item always show the FAB
                if (!recyclerView.canScrollVertically(-1) && viewModel.dashboardLiveData.value!!.isNotEmpty()) {
                    binding.cardView.visibility = VISIBLE
                } else {
                    binding.cardView.visibility = GONE
                }
            }
        })
    }

    override fun onClick(v: View?) {
        if (v == binding.imgListView || v == binding.txtListView) {
            if (gridLayoutManager.spanCount != 1) {
                gridLayoutManager.spanCount = 1

                dashboardAdapter.notifyItemRangeChanged(0, dashboardAdapter.itemCount)

                // Setting color and icons on click event
                binding.txtListView.setTextColor(getColor(R.color.colorPrimary))
                binding.imgListView.setImageResource(R.drawable.ic_action_list_view_green)

                binding.txtGridView.setTextColor(getColor(R.color.colorBlack))
                binding.imgGridView.setImageResource(R.drawable.ic_action_grid_view)

                // clearing edit text focus and hiding keyboard for better view
                binding.edtSearch.clearFocus()
                AppUtils.hideSoftKeyBoard(mContext, binding.imgSearch)
            }
        } else if (v == binding.imgGridView || v == binding.txtGridView) {
            if (gridLayoutManager.spanCount != 2) {
                gridLayoutManager.spanCount = 2

                dashboardAdapter.notifyItemRangeChanged(0, dashboardAdapter.itemCount)

                // Setting color and icons on click event
                binding.txtListView.setTextColor(getColor(R.color.colorBlack))
                binding.imgListView.setImageResource(R.drawable.ic_action_list_view)

                binding.txtGridView.setTextColor(getColor(R.color.colorPrimary))
                binding.imgGridView.setImageResource(R.drawable.ic_action_grid_view_green)

                // clearing edit text focus and hiding keyboard for better view
                binding.edtSearch.clearFocus()
                AppUtils.hideSoftKeyBoard(mContext, binding.imgSearch)
            }
        } else if (v == binding.layoutToolbar.imgExit) {
            // display Exit Dialog
            showDialog(getString(R.string.exit), getString(R.string.do_you_really_want_to_n_exit_form_app))
        } else if (v == binding.imgSearch) {
            // clearing edit text focus and hiding keyboard for better view
            binding.edtSearch.clearFocus()
            AppUtils.hideSoftKeyBoard(mContext, binding.imgSearch)

//            viewModel.searchData(binding.edtSearch.text.toString())
        }
    }

    private fun showDialog(title: String, message: String) {
        val dialog = Dialog(mContext)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)

        val bind: LayoutDialogExitBinding = LayoutDialogExitBinding.inflate(LayoutInflater.from(mContext))
        dialog.setContentView(bind.root)

        bind.txtTitle.text = title
        bind.txtMessage.text = message

        if (title == getString(R.string.internet)) {
            bind.txtNo.visibility = GONE
        }

        bind.txtNo.setOnClickListener {
            dialog.dismiss()
        }

        bind.txtYes.setOnClickListener {
            dialog.dismiss()

            finishAffinity()
        }

        dialog.show()
    }
}