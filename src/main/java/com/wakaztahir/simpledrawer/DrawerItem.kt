package com.wakaztahir.simpledrawer

import android.graphics.drawable.Drawable

open class DrawerItem(private val drawer: SimpleDrawer,val name:String,val tag:String, val type: String, initValues: MutableMap<String, String>) {

    var values = initValues
        set(newValues) {
            field = newValues
            drawer.updateItem(this)
        }

    var itemIcon: Drawable? = null
        set(icon) {
            field = icon
            drawer.updateItem(this)
        }
    var active: Boolean = false
        set(value) {
            field = value
            drawer.updateItem(this)
        }
}