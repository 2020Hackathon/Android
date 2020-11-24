package com.example.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.hackathon.Fragment.Adapter.FragmentAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tab()
        field_spinner()
        date_spinner()
    }
    fun tab(){
        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        list_viewpager.adapter = fragmentAdapter

        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("프로젝트")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("대회")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("포트폴리오")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("자기소개서")))

        list_viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                Log.e("a", p0.toString())

                if (p0 != null){
                    list_viewpager.currentItem = p0.position
                }
            }

        })
    }

    private fun createTabView(tabName: String): View {
        val tabView = LayoutInflater.from(baseContext).inflate(R.layout.custom_tab, null)

        val txt_name = tabView.findViewById(R.id.txt_name) as TextView

        txt_name.text = tabName
        return tabView
    }
    fun field_spinner(){
        val items = resources.getStringArray(R.array.field_array)

        var field_spinner_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        category.adapter = field_spinner_adapter
    }
    fun date_spinner(){
        val items = resources.getStringArray(R.array.date_array)

        var date_spinner_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        date_spinner.adapter = date_spinner_adapter
    }
}