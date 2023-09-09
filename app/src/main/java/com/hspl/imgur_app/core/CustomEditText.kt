package com.hspl.imgur_app.core

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.hspl.imgur_app.R
import com.hspl.imgur_app.utils.TypeFactory

class CustomEditText : AppCompatEditText {

    private var mFontFactory: TypeFactory? = null

    constructor(context: Context) : super(context) {
        this.setWillNotDraw(false)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.setWillNotDraw(false)

        applyCustomFont(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) :
            super(context, attrs, defStyle) {
        this.setWillNotDraw(false)

        applyCustomFont(context, attrs)
    }

    private fun applyCustomFont(context: Context, attrs: AttributeSet) {

        val array = context.theme.obtainStyledAttributes(
            attrs, R.styleable.CustomFont,
            0, 0
        )

        val typefaceType: Int = try {
            array.getInteger(R.styleable.CustomFont_font_name, 0)
        } finally {
            array.recycle()
        }
        if (!isInEditMode) {
            typeface = getTypeFace(typefaceType)
        }
    }

    private fun getTypeFace(type: Int): Typeface? {
        if (mFontFactory == null) mFontFactory = TypeFactory(context)

        return when (type) {
            Constants.R_BOLD -> mFontFactory!!.bold
            else -> mFontFactory!!.regular
        }
    }

    interface Constants {
        companion object {
            const val R_BOLD = 1
        }
    }
}