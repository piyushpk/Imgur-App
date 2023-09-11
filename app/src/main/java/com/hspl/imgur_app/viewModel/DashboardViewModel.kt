package com.hspl.imgur_app.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hspl.imgur_app.model.DashboardModel

class DashboardViewModel : ViewModel() {
    val dashboardLiveData = MutableLiveData<List<DashboardModel>>()
    val errorMessage = MutableLiveData<String>()

    fun getDataList() {
        val dashboardModel: ArrayList<DashboardModel> = ArrayList()

        dashboardModel.add(DashboardModel("Image Title 1", "", "", "10"))
        dashboardModel.add(DashboardModel("Image Title 1", "", "", "10"))
        dashboardModel.add(DashboardModel("Image Title 1", "", "", "10"))
        dashboardModel.add(DashboardModel("Image Title 1", "", "", "10"))

        dashboardLiveData.postValue(dashboardModel)
    }
}