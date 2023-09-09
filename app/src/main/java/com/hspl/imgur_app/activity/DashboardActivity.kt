package com.hspl.imgur_app.activity

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.hspl.imgur_app.core.ParentActivity
import com.hspl.imgur_app.databinding.ActivityDashboardBinding
import com.hspl.imgur_app.viewModel.DashboardViewModel

class DashboardActivity : ParentActivity() {

    private lateinit var mContext: Context
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var viewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mContext = this

        viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]
    }
}