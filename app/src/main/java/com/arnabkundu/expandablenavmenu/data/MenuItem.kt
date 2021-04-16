package com.arnabkundu.expandablenavmenu.data

data class MenuItem(
    val sl:String,
    val menu:String,
    val subMenu: ArrayList<MenuItem>?
)
