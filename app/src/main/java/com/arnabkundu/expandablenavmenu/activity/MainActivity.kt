package com.arnabkundu.expandablenavmenu.activity

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.arnabkundu.expandablenavmenu.R
import com.arnabkundu.expandablenavmenu.data.MenuItem
import com.arnabkundu.expandablenavmenu.data.SubMenuItem
import com.arnabkundu.expandablenavmenu.fragment.FragmentMenu
import com.arnabkundu.expandablenavmenu.fragment.FragmentSubMenu
import com.arnabkundu.expandablenavmenu.listener.FragmentListener
import com.arnabkundu.expandablenavmenu.listener.ObjectListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_layout.*


class MainActivity : AppCompatActivity() {

    private val itemList:ArrayList<MenuItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemList.add(MenuItem("1","Menu 1",null))
        itemList.add(MenuItem("2","Menu 2",null))
        itemList.add(MenuItem("3","Menu 3", arrayListOf(
                MenuItem("10","AAA",null),
                MenuItem("11","AAA",null)
        )))
        itemList.add(MenuItem("4","Menu 4",null))
        itemList.add(MenuItem("5","Menu 5", arrayListOf(
                MenuItem("12","BBB",null),
                MenuItem("13","BBB",null)
        )))

        menuBtn.setOnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }


        supportFragmentManager.beginTransaction()
            .add(R.id.fragContainer, FragmentMenu(itemList, object : FragmentListener {
                override fun getObject(obj: Any?) {
                    val data = obj as MenuItem
                    if (data.subMenu != null) {
                        replaceFragment(FragmentSubMenu(data.menu, data.subMenu, object : FragmentListener {
                            override fun getObject(obj: Any?) {
                                val subData = obj as MenuItem
                                if (subData.subMenu != null) {

                                } else {
                                    Toast.makeText(this@MainActivity, subData.menu, Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onBack() {
                                onBackPressed()
                            }
                        }))
                    } else {
                        Toast.makeText(this@MainActivity, data.menu, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onBack() {
                    onBackPressed()
                }
            }))
                .commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in,R.anim.fade_out,R.anim.fade_in,R.anim.slide_out)
        transaction.addToBackStack(null)
        transaction.replace(R.id.fragContainer, fragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        when {
            supportFragmentManager.backStackEntryCount > 0 -> {
                supportFragmentManager.popBackStack()
            }
            drawer_layout.isDrawerOpen(GravityCompat.START) -> {
                drawer_layout.closeDrawer(GravityCompat.START)
            }
            else -> {
                finish()
            }
        }
    }
}