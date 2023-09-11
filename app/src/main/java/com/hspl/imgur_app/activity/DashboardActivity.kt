package com.hspl.imgur_app.activity

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.Window
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.hspl.imgur_app.adapter.DashboardAdapter
import com.hspl.imgur_app.core.ParentActivity
import com.hspl.imgur_app.databinding.ActivityDashboardBinding
import com.hspl.imgur_app.databinding.LayoutDialogExitBinding
import com.hspl.imgur_app.viewModel.DashboardViewModel

class DashboardActivity : ParentActivity(), OnClickListener {

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

        binding.layoutToolbar.imgExit.setOnClickListener(this)
        binding.imgSearch.setOnClickListener(this)
        binding.imgListView.setOnClickListener(this)
        binding.txtListView.setOnClickListener(this)
        binding.imgGridView.setOnClickListener(this)
        binding.txtGridView.setOnClickListener(this)

        prepareRecyclerView()

        viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]

        viewModel.getDataList()
        viewModel.dashboardLiveData.observe(this) { list ->
            dashboardAdapter.setTicketList(list)
        }
    }

    private fun prepareRecyclerView() {
        gridLayoutManager = GridLayoutManager(mContext, 1)
        dashboardAdapter = DashboardAdapter(gridLayoutManager)

        binding.recyclerView.apply {
            layoutManager = gridLayoutManager
            adapter = dashboardAdapter
        }
    }

    override fun onClick(v: View?) {
        if (v == binding.imgListView || v == binding.txtListView) {
            if (gridLayoutManager.spanCount != 1) {
                gridLayoutManager.spanCount = 1

                dashboardAdapter.notifyItemRangeChanged(0, dashboardAdapter.itemCount)
            }
        } else if (v == binding.imgGridView || v == binding.txtGridView) {
            if (gridLayoutManager.spanCount != 2) {
                gridLayoutManager.spanCount = 2

                dashboardAdapter.notifyItemRangeChanged(0, dashboardAdapter.itemCount)
            }
        } else if (v == binding.layoutToolbar.imgExit) {
            showDialog()
        }
    }

    private fun showDialog() {
        val dialog = Dialog(mContext)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)

        val bind: LayoutDialogExitBinding = LayoutDialogExitBinding.inflate(LayoutInflater.from(mContext))
        dialog.setContentView(bind.root)

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