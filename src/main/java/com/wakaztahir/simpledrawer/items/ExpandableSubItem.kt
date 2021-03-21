package com.wakaztahir.simpledrawer.items

import com.wakaztahir.simpledrawer.DrawerItem
import com.wakaztahir.simpledrawer.SimpleDrawer

class ExpandableSubItem(drawer: SimpleDrawer, name: String, tag: String, values: MutableMap<String, String>, var parentItem: ExpandableItem) :
        DrawerItem(drawer, name, tag, "subitem", values) {

}