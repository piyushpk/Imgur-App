package com.hspl.imgur_app.model

import com.google.gson.annotations.SerializedName

data class DashboardModel(
    val title: String,
    val datetime: String,
    val images_count: String,

    @SerializedName("images")
    val imagesModel: List<ImagesModel>
)