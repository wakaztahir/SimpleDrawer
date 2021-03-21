package com.wakaztahir.simpledrawer.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wakaztahir.simpledrawer.DrawerItem
import com.wakaztahir.simpledrawer.databinding.DrawerNavExpandableItemBinding
import com.wakaztahir.simpledrawer.databinding.DrawerNavPrimaryItemBinding
import com.wakaztahir.simpledrawer.databinding.DrawerNavSectionItemBinding
import com.wakaztahir.simpledrawer.items.ExpandableItem
import com.wakaztahir.simpledrawer.items.ExpandableSubItem
import com.wakaztahir.simpledrawer.items.PrimaryItem
import com.wakaztahir.simpledrawer.items.SectionItem
import com.wakaztahir.simpledrawer.utils.DrawerColors

class NavigationAdapter(
    private val context: Context,
    var itemList: MutableList<DrawerItem>,
    val themeColor: DrawerColors
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var navigationActionListener = object : NavigationActionListener {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            2 -> { //Section
                val binding = DrawerNavSectionItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SectionViewHolder(binding)
            }
            3 -> { //Primary
                val binding = DrawerNavPrimaryItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                PrimaryViewHolder(binding)
            }
            4 -> { //Expandable
                val binding = DrawerNavExpandableItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ExpandableViewHolder(binding)
            }
            else -> {
                val binding = DrawerNavSectionItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SectionViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemList[position]
        when (holder.itemViewType) {
            2 -> { //Section
                holder as SectionViewHolder
                with(holder.binding) {
                    sectionItemText.text = item.name
                    sectionItemContainer.setOnClickListener {
                        navigationActionListener.onSectionItemClick(item as SectionItem, item.tag)
                    }
                }

            }
            3 -> { //Primary
                holder as PrimaryViewHolder
                item as PrimaryItem
                with(holder.binding) {
                    primaryItemText.text = item.name
                    if (item.itemIcon != null) {
                        primaryItemIcon.setImageDrawable(item.itemIcon)
                    }
                    if (item.active) {
                        root.backgroundTintList =
                            ColorStateList.valueOf(themeColor.activeItemBackgroundColor)
                        primaryItemText.setTextColor(themeColor.activeItemTextColor)
                        primaryItemIcon.imageTintList =
                            ColorStateList.valueOf(themeColor.activeItemIconTint);
                    } else {
                        primaryItemText.setTextColor(themeColor.itemTextColor)
                        root.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                context,
                                android.R.color.transparent
                            )
                        )
                        primaryItemIcon.imageTintList =
                            ColorStateList.valueOf(themeColor.itemIconTint)
                    }
                    if (item.actionIcon != null) {
                        primaryItemAction.visibility = View.VISIBLE
                        primaryItemAction.setImageDrawable(item.itemIcon)
                        primaryItemAction.setOnClickListener {
                            navigationActionListener.onItemActionClick(
                                item,
                                item.tag,
                                item.section
                            )
                        }
                    }
                    primaryItemContainer.setOnClickListener {
                        navigationActionListener.onItemClick(
                            item,
                            item.tag,
                            item.section
                        )
                    }
                }

            }
            4 -> { //Expandable
                holder as ExpandableViewHolder
                item as ExpandableItem
                with(holder.binding) {
                    expandableItemText.text = item.name
                    if (item.itemIcon != null) {
                        expandableItemIcon.setImageDrawable(item.itemIcon)
                    }
                    if (item.active) {
                        root.backgroundTintList =
                            ColorStateList.valueOf(themeColor.activeItemBackgroundColor)
                        expandableItemText.setTextColor(themeColor.activeItemTextColor)
                        expandableItemIcon.imageTintList =
                            ColorStateList.valueOf(themeColor.activeItemIconTint);
                    } else {
                        expandableItemText.setTextColor(themeColor.itemTextColor)
                        root.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                context,
                                android.R.color.transparent
                            )
                        )
                        expandableItemIcon.imageTintList =
                            ColorStateList.valueOf(themeColor.itemIconTint)
                    }
                    if (item.expanded) {
                        expandableLayout.isExpanded = true
                    }
                    expandableItemExpandBtn.setOnClickListener {
                        if (expandableLayout.state == 0) {
                            expandableItemExpandBtn.animate().rotation(270f).duration = 300
                        } else {
                            expandableItemExpandBtn.animate().rotation(90f).duration = 300
                        }
                        expandableLayout.toggle()
                    }
                    subitemsRecyclerView.layoutManager = LinearLayoutManager(context)
                    subitemsRecyclerView.adapter = item.subitemsAdapter
                    item.subitemsAdapter.expandableSubItemListener =
                        object : ExpandableSubItemsAdapter.ExpandableSubItemListener {
                            override fun onClick(
                                subitem: ExpandableSubItem,
                                subtag: String,
                                parent: ExpandableItem
                            ) {
                                navigationActionListener.onSubItemClick(subitem, subtag, parent)
                            }
                        }
                    expandableItemContainer.setOnClickListener {
                        navigationActionListener.onItemClick(item, item.tag, item.section)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position].type) {
            "section" -> 2
            "primary" -> 3
            "expandable" -> 4
            else -> 2
        }
    }

    //View Holders

    class SectionViewHolder(val binding: DrawerNavSectionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    class PrimaryViewHolder(val binding: DrawerNavPrimaryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    class ExpandableViewHolder(val binding: DrawerNavExpandableItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    interface NavigationActionListener {
        fun onSectionItemClick(item: SectionItem, tag: String) {}
        fun onItemClick(item: DrawerItem, tag: String, section: SectionItem?) {}
        fun onItemActionClick(item: DrawerItem, tag: String, section: SectionItem?) {}
        fun onSubItemClick(
            subitem: ExpandableSubItem,
            subitemTag: String,
            parent: ExpandableItem
        ) {
        }
    }
}