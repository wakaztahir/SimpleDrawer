package com.wakaztahir.simpledrawer.utils

import android.graphics.Color

data class DrawerColors(var colorValues: MutableMap<String, Int> = mutableMapOf()) {
    var itemBackgroundColor: Int = Color.TRANSPARENT
    var itemTextColor: Int = Color.GRAY
    var itemIconTint: Int = Color.GRAY

    var activeItemBackgroundColor: Int = Color.GRAY
    var activeItemTextColor: Int = Color.YELLOW
    var activeItemIconTint: Int = Color.YELLOW


    init {
        if (colorValues.containsKey("itemBackgroundColor")) {
            itemBackgroundColor = colorValues["itemBackgroundColor"]!!
        }
        if (colorValues.containsKey("itemTextColor")) {
            itemTextColor = colorValues["itemTextColor"]!!
        }
        if (colorValues.containsKey("itemIconTint")) {
            itemIconTint = colorValues["itemIconTint"]!!
        }
        if (colorValues.containsKey("activeItemBackgroundColor")) {
            activeItemBackgroundColor = colorValues["activeItemBackgroundColor"]!!
        }
        if (colorValues.containsKey("activeItemTextColor")) {
            activeItemTextColor = colorValues["activeItemTextColor"]!!
        }
        if (colorValues.containsKey("activeItemIconTint")) {
            activeItemIconTint = colorValues["activeItemIconTint"]!!
        }
    }
}