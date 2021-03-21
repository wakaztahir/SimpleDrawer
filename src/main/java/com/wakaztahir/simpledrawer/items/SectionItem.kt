package com.wakaztahir.simpledrawer.items

import com.wakaztahir.simpledrawer.DrawerItem
import com.wakaztahir.simpledrawer.SimpleDrawer

class SectionItem(drawer: SimpleDrawer,name:String,tag:String, values:MutableMap<String,String>):
        DrawerItem(drawer,name,tag,"section",values) {

}