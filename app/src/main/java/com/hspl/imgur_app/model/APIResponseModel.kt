package com.hspl.imgur_app.model

import com.google.gson.annotations.SerializedName

class APIResponseModel {

    @SerializedName("data")
    lateinit var dashboardModel: List<DashboardModel>
}