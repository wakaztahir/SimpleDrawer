package com.wakaztahir.simpledrawer.items

import com.wakaztahir.simpledrawer.DrawerItem
import com.wakaztahir.simpledrawer.SimpleDrawer
import com.wakaztahir.simpledrawer.adapters.ExpandableSubItemsAdapter

class ExpandableItem(drawer: SimpleDrawer, name: String, tag: String, values: MutableMap<String, String>) :
        DrawerItem(drawer, name, tag, "expandable", values) {
    var subitemsAdapter = ExpandableSubItemsAdapter(ArrayList())
    var section: SectionItem? = null
    var expanded: Boolean = false
}