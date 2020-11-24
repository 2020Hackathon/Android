package com.example.hackathon

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.hackathon.DTO.ContentData
import com.example.hackathon.Fragment.Adapter.FragmentAdapter
import com.example.hackathon.Fragment.ContentListModel
import com.example.hackathon.User.MypageActivity
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom.*
import kotlinx.android.synthetic.main.listview_item.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    var database = FirebaseDatabase.getInstance()
    val contestRef = database.getReference("contest")
    val introduction = database.getReference("introduction")
    val project = database.getReference("project")
    val protpolio = database.getReference("protpolio")
    var dia_select = ""
    var dia_select1 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tab()
        mypage()
        flat()
    }
    fun tab(){
        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        list_viewpager.adapter = fragmentAdapter

        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("프로젝트")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("대회")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("포트폴리오")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("자기소개서")))

        list_viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                Log.e("a", p0.toString())

                if (p0 != null) {
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
    fun flat(){
        fab_button.setOnClickListener {
            val inflater =  getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view =  inflater.inflate(R.layout.dialog_custom, null)
            val items = resources.getStringArray(R.array.field_array)
            val items1 = resources.getStringArray(R.array.menu_array)
            val alertDialog = AlertDialog.Builder(this)
                .setTitle("게시판")
                .create()

            val diaEdit: EditText = view.findViewById(R.id.dia_title)
            val diaEdit2: EditText = view.findViewById(R.id.dia_contents)
            val diabutton1 : Button = view.findViewById(R.id.ok)
            val diabutton2 : Button = view.findViewById(R.id.cancel)
            val diaSpinner : Spinner = view.findViewById(R.id.dia_spinner)
            val diaSpinner1 : Spinner = view.findViewById(R.id.dia_spinner1)
            var field_spinner_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)

            var arrayList = ArrayList<ContentListModel>()
            diaSpinner.adapter = field_spinner_adapter

            diaSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, l: Long) {
                    dia_select = items[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
            var field_spinner_adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item,items1)
            diaSpinner1.adapter = field_spinner_adapter1

            diaSpinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    dia_select1 = items1[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

            contestRef.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    arrayList = snapshot.getValue(ContentData::class.java)!!.list
                }

                override fun onCancelled(error: DatabaseError) {}

            })
            introduction.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    arrayList = snapshot.getValue(ContentData::class.java)!!.list
                }

                override fun onCancelled(error: DatabaseError) {}

            })
            project.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    arrayList = snapshot.getValue(ContentData::class.java)!!.list
                }

                override fun onCancelled(error: DatabaseError) {}

            })
            protpolio.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    arrayList = snapshot.getValue(ContentData::class.java)!!.list
                }

                override fun onCancelled(error: DatabaseError) {}

            })
            diabutton1.setOnClickListener {
                var diatitle = diaEdit.text.toString()
                var diacontents = diaEdit2.text.toString()
                when(dia_select1){
                    "포트폴리오" -> {
                        arrayList.add(ContentListModel(diatitle, diacontents, dia_select))
                        protpolio.setValue(ContentData(arrayList))
                    }
                    "프로젝트" -> {
                        arrayList.add(ContentListModel(diatitle, diacontents, dia_select))
                        project.setValue(ContentData(arrayList))
                    }
                    "대회" -> {
                        arrayList.add(ContentListModel(diatitle, diacontents, dia_select))
                        contestRef.setValue(ContentData(arrayList))
                    }
                    "자기소개서" -> {
                        arrayList.add(ContentListModel(diatitle, diacontents, dia_select))
                        introduction.setValue(ContentData(arrayList))
                    }

                }

                alertDialog.dismiss()
            }
            diabutton2.setOnClickListener {

                alertDialog.dismiss()
            }

            alertDialog.setView(view)
            alertDialog.show()
        }
    }
    fun mypage(){
        join.setOnClickListener {
            val intent = Intent(this@MainActivity,FileInfoActivity::class.java)
            startActivity(intent)
        }
        my_page.setOnClickListener {
            val id = intent.getStringExtra("id")
            var joinString = intent.getStringExtra("join")
            Log.d("id",id.toString())
            val intent = Intent(this@MainActivity, MypageActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("join",joinString)
            startActivity(intent)
        }
    }
}