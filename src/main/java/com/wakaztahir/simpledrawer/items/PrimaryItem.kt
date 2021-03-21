package com.wakaztahir.simpledrawer.items

import android.graphics.drawable.Drawable
import com.wakaztahir.simpledrawer.DrawerItem
import com.wakaztahir.simpledrawer.SimpleDrawer

class PrimaryItem(
    private val drawer: SimpleDrawer,
    name: String,
    tag: String,
    values: MutableMap<String, String>
) :
    DrawerItem(drawer, name, tag, "primary", values) {

    var section: SectionItem? = null

    var actionIcon: Drawable? = null
        set(icon) {
            field = icon
            drawer.updateItem(this)
        }
}