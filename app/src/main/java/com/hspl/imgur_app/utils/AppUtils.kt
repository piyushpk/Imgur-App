package com.hspl.imgur_app.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.text.SimpleDateFormat
import java.util.*

class AppUtils {

    companion object {

        fun hideSoftKeyBoard(context: Context, view: View) {
            try {
                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @SuppressLint("SimpleDateFormat")
        fun convertDateTimeToDash(originalDate: String): String {
            val date = SimpleDateFormat("dd/MM/yyyy").parse(originalDate)

            return SimpleDateFormat("yyyy-MM-dd").format(date!!)
        }

        fun apiResponseCodeCheck(responseCode: Int): String {
            return if (responseCode >= 502) {
                "Service Temporarily Unavailable"
            } else if (responseCode >= 500) {
                "Internal Server Error"
            } else if (responseCode >= 400) {
                "Requested URL/API not found"
            } else {
                "Something went wrong. Try again later"
            }
        }
    }
}