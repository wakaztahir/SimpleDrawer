package com.wakaztahir.simpledrawer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wakaztahir.simpledrawer.R
import com.wakaztahir.simpledrawer.items.ExpandableItem
import com.wakaztahir.simpledrawer.items.ExpandableSubItem

class ExpandableSubItemsAdapter (var itemList:MutableList<ExpandableSubItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var expandableSubItemListener = object : ExpandableSubItemListener {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.drawer_nav_expandable_subitem,parent,false)
        return ExpandableSubItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ExpandableSubItemViewHolder
        val item = itemList[position]
        holder.subitemText.text = item.name
        if (item.itemIcon != null) {
            holder.subitemIcon.setImageDrawable(item.itemIcon)
        }
        holder.subitemContainer.setOnClickListener {
            expandableSubItemListener.onClick(item,item.tag,item.parentItem)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    //View Holders
    class ExpandableSubItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val subitemContainer: LinearLayout = itemView.findViewById(R.id.subitem_container)
        val subitemText: TextView = itemView.findViewById(R.id.subitem_text)
        val subitemIcon: ImageView = itemView.findViewById(R.id.subitem_icon)
    }

    interface ExpandableSubItemListener{
        fun onClick(subitem: ExpandableSubItem, subtag: String, parent: ExpandableItem){}
    }
}