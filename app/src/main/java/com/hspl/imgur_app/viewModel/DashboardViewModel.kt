package com.hspl.imgur_app.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hspl.imgur_app.model.APIResponseModel
import com.hspl.imgur_app.model.DashboardModel
import com.hspl.imgur_app.utils.AppUtils
import com.hspl.imgur_app.utils.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel : ViewModel() {
    // To save complete response of API
    var masterLiveData = APIResponseModel()

    // Only searched data will save in this list
    val dashboardLiveData = MutableLiveData<List<DashboardModel>>()

    // To manage the API messages
    val messages = MutableLiveData<String>()

    // This is the API call to get data from API
    fun getDataList() {
        RetrofitInstance.retrofitInterface.getImagesData().enqueue(object : Callback<APIResponseModel> {
            override fun onResponse(call: Call<APIResponseModel>, response: Response<APIResponseModel>) {
                if (response.body() != null) {
                    masterLiveData = response.body()!!

                    messages.postValue("Success")
                } else {
                    messages.postValue(AppUtils.apiResponseCodeCheck(response.code()))
                }
            }

            override fun onFailure(call: Call<APIResponseModel>, t: Throwable) {
                messages.postValue(t.message.toString())
                return
            }
        })
    }

    // To perform search action
    fun searchData(searchText: String) {
        val temp: ArrayList<DashboardModel> = ArrayList()

        if (searchText.isNotEmpty()) {
            for (item in masterLiveData.dashboardModel) {
                if (item.title.lowercase().contains(searchText.lowercase())) {
                    temp.add(item)
                }
            }
        }

        dashboardLiveData.postValue(temp)
    }
}