package com.wakaztahir.simpledrawer

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.wakaztahir.simpledrawer.adapters.NavigationAdapter
import com.wakaztahir.simpledrawer.items.ExpandableItem
import com.wakaztahir.simpledrawer.items.ExpandableSubItem
import com.wakaztahir.simpledrawer.items.PrimaryItem
import com.wakaztahir.simpledrawer.items.SectionItem
import com.wakaztahir.simpledrawer.utils.DrawerColors

class SimpleDrawer(
    var context: Context,
    val drawer: DrawerLayout,
    navView: NavigationView,
    headerView: View?,
    themeColor: DrawerColors = DrawerColors()
) {

    private val navAdapter = NavigationAdapter(context, ArrayList(), themeColor)

    var navigationActionListener = navAdapter.navigationActionListener
        get() {
            return navAdapter.navigationActionListener
        }
        set(listener) {
            navAdapter.navigationActionListener = listener
            field = listener
        }


    init {
        val container = LinearLayoutCompat(context)
        container.orientation = LinearLayoutCompat.VERTICAL
        container.layoutParams = LinearLayoutCompat.LayoutParams(
            LinearLayoutCompat.LayoutParams.MATCH_PARENT,
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        )
        if (headerView != null) {
            container.addView(headerView)
        }
        val mainRecyclerView = RecyclerView(context)
        mainRecyclerView.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        mainRecyclerView.layoutManager = LinearLayoutManager(context)
        mainRecyclerView.adapter = navAdapter
        container.addView(mainRecyclerView)
        navView.addView(container)
    }

    fun findItemByTag(tag: String): DrawerItem? {
        return navAdapter.itemList.find { item -> item.tag == tag }
    }

    fun updateItem(item: DrawerItem) {
        if (navAdapter.itemList.contains(item)) {
            val pos = navAdapter.itemList.indexOf(item)
            navAdapter.itemList[pos] = item
            navAdapter.notifyItemChanged(pos)
        }
    }

    fun removeItem(item: DrawerItem) {
        val itemIndex = navAdapter.itemList.indexOf(item)
        navAdapter.itemList.removeAt(itemIndex)
        navAdapter.notifyItemRemoved(itemIndex)
    }

    fun sectionItem(name: String, tag: String): SectionItem {
        val sectionItem = SectionItem(this, name, tag, mutableMapOf())
        navAdapter.itemList.add(sectionItem)
        navAdapter.notifyItemInserted(navAdapter.itemCount - 1)
        return sectionItem
    }

    fun primaryItem(
        name: String,
        tag: String,
        section: SectionItem?,
        itemIcon: Drawable?,
        actionIcon: Drawable? = null
    ): PrimaryItem {
        val primaryItem = PrimaryItem(this, name, tag, mutableMapOf())
        primaryItem.section = section
        primaryItem.itemIcon = itemIcon
        primaryItem.actionIcon = actionIcon
        navAdapter.itemList.add(primaryItem)
        navAdapter.notifyItemInserted(navAdapter.itemCount - 1)
        return primaryItem
    }

    fun expandableItem(
        name: String,
        tag: String,
        section: SectionItem?,
        itemIcon: Drawable?
    ): ExpandableItem {
        val expandableItem = ExpandableItem(this, name, tag, mutableMapOf())
        expandableItem.section = section
        expandableItem.itemIcon = itemIcon
        navAdapter.itemList.add(expandableItem)
        navAdapter.notifyItemInserted(navAdapter.itemCount - 1)
        return expandableItem
    }

    fun addSubItem(
        parentItem: ExpandableItem,
        name: String,
        tag: String,
        itemIcon: Drawable?
    ): ExpandableSubItem {
        val subItem = ExpandableSubItem(this, name, tag, mutableMapOf(), parentItem)
        subItem.itemIcon = itemIcon
        parentItem.subitemsAdapter.itemList.add(subItem)
        navAdapter.notifyItemChanged(navAdapter.itemList.indexOf(parentItem))
        parentItem.subitemsAdapter.notifyItemInserted(parentItem.subitemsAdapter.itemCount - 1)
        return subItem
    }
}