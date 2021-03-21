package com.wakaztahir.simpledrawer.utils

import android.content.Context
import android.util.TypedValue

object Helpers {
    fun dpToPx(context: Context, dp: Float): Int {
        val metrics = context.resources.displayMetrics
        val px = dp * (metrics.densityDpi / 160f)
        return px.toInt()
    }

    fun pxToDp(context: Context, px: Float): Int {
        return (px / context.resources.displayMetrics.density).toInt()
    }

    fun getAttrValue(context: Context, attr: Int): TypedValue {
        val value = TypedValue()
        val theme = context.theme
        theme.resolveAttribute(attr, value, true)
        return value
    }
}