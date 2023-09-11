package com.hspl.imgur_app.utils

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.hspl.imgur_app.databinding.LayoutProgressDialogBinding
import java.text.SimpleDateFormat
import java.util.*

class AppUtils {

    companion object {

        // Call to hide the soft keyboard
        fun hideSoftKeyBoard(context: Context, view: View) {
            try {
                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // To convert EPOS datetime format to DD/MM/YYYY HH:SS AM/PM
        fun convertDateTime(time: String): String {
            return if (time.isNotEmpty()) {
                val tempTime: Long = time.toLong()
                val format = "dd/MM/yyyy HH:mm a"
                val sdf = SimpleDateFormat(format, Locale.getDefault())

                sdf.timeZone = TimeZone.getDefault()
                sdf.format(Date(tempTime * 1000))
            } else {
                ""
            }
        }

        // This is to handle API error responses
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

        @SuppressLint("InflateParams")
        fun progressDialog(context: Context): Dialog {
            val dialog = Dialog(context)

            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val bind: LayoutProgressDialogBinding = LayoutProgressDialogBinding.inflate(inflater)
            dialog.setContentView(bind.root)

            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            return dialog
        }
    }
}