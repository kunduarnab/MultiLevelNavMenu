package com.arnabkundu.expandablenavmenu.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.arnabkundu.expandablenavmenu.R
import com.arnabkundu.expandablenavmenu.adapter.AdapterMenu
import com.arnabkundu.expandablenavmenu.data.MenuItem
import com.arnabkundu.expandablenavmenu.listener.FragmentListener
import com.arnabkundu.expandablenavmenu.listener.ObjectListener
import com.arnabkundu.expandablenavmenu.util.RecyclerViewMargin
import kotlinx.android.synthetic.main.fragment_menu.*

class FragmentMenu(private val itemList:ArrayList<MenuItem>, val listener: FragmentListener) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.itemAnimator = DefaultItemAnimator()
        val decoration = RecyclerViewMargin(0, 1)
        recyclerView.addItemDecoration(decoration)
        val adapter = AdapterMenu(requireContext(), itemList, object:ObjectListener{
            override fun getObject(obj: Any?) {
                listener.getObject(obj)
            }
        })
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}