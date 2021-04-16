package com.arnabkundu.expandablenavmenu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.arnabkundu.expandablenavmenu.R
import com.arnabkundu.expandablenavmenu.data.MenuItem
import com.arnabkundu.expandablenavmenu.listener.ObjectListener
import kotlinx.android.synthetic.main.item_menu.view.*

class AdapterMenu(val c: Context, private var itemList: ArrayList<MenuItem>, private val listener: ObjectListener):
    RecyclerView.Adapter<AdapterMenu.HolderItem>(){

    class HolderItem(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.name
        val arrow: ImageView = itemView.arrow
        val row: RelativeLayout = itemView.row
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderItem {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return HolderItem(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: HolderItem, position: Int) {
        val item : MenuItem = itemList[position]
        holder.name.text = item.menu
        if(item.subMenu!=null){
            holder.arrow.visibility = View.VISIBLE
        }
        holder.row.setOnClickListener {
            listener.getObject(item)
        }
    }
}