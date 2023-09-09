package com.hspl.imgur_app.utils

import android.content.Context
import android.graphics.Typeface

class TypeFactory(context: Context) {

    private val fontBold = "Amaranth-Bold.ttf"
    var bold: Typeface? = Typeface.createFromAsset(context.assets, fontBold)

    private val fontRegular = "Amaranth-Regular.ttf"
    var regular: Typeface? = Typeface.createFromAsset(context.assets, fontRegular)
}