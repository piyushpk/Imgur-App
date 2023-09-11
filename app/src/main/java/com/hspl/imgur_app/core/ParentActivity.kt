package com.hspl.imgur_app.core

import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hspl.imgur_app.utils.AppUtils

open class ParentActivity : AppCompatActivity() {

    private lateinit var progressDialog: Dialog

    protected fun isInternetConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.d("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }

                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.d("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }

                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.d("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    // To display toast message
    fun showToast(context: Context?, msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    // To display Loading dialog
    fun showLoadingDialog(context: Context) {
        progressDialog = AppUtils.progressDialog(context)
        progressDialog.show()
    }

    // To dismiss Loading dialog
    fun dismissLoadingDialog() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }
}