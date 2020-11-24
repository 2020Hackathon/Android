package com.example.hackathon.Fragment.Field

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.hackathon.DTO.ContentData
import com.example.hackathon.Fragment.Adapter.FieldAdapter
import com.example.hackathon.Fragment.ContentListModel
import com.example.hackathon.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.android.synthetic.main.fragment_competition.*
import kotlinx.android.synthetic.main.fragment_competition.view.*
import kotlinx.android.synthetic.main.listview_item.*

class CompetitionFragment : Fragment() {
    var database = FirebaseDatabase.getInstance()
    val contestRef = database.getReference("contest")
    var arrayList = ArrayList<ContentListModel>()
    var dia_select1 = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_competition, container, false)
        val items = resources.getStringArray(R.array.field_array)
        var arrayList = ArrayList<ContentListModel>()

        var field_spinner_adapter =
            context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, items) }
        view.com_category.adapter = field_spinner_adapter

        setData()

        return view
    }

    fun setData(){
        contestRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                arrayList = snapshot.getValue(ContentData::class.java)!!.list
                contestList.adapter = context?.let { FieldAdapter(it, arrayList) }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}