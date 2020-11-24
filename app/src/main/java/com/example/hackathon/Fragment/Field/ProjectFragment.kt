package com.example.hackathon.Fragment.Field

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.hackathon.DTO.ContentData
import com.example.hackathon.Fragment.Adapter.FieldAdapter
import com.example.hackathon.Fragment.ContentListModel
import com.example.hackathon.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_competition.*
import kotlinx.android.synthetic.main.fragment_competition.contestList
import kotlinx.android.synthetic.main.fragment_competition.view.*
import kotlinx.android.synthetic.main.fragment_competition.view.com_category
import kotlinx.android.synthetic.main.fragment_project.*
import kotlinx.android.synthetic.main.fragment_project.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class ProjectFragment : Fragment() {
    var database = FirebaseDatabase.getInstance()
    val projectRef = database.getReference("project")
    var arrayList = ArrayList<ContentListModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_project, container, false)
        val items = resources.getStringArray(R.array.field_array)

        var field_spinner_adapter =
            context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, items) }
        view.pro_category.adapter = field_spinner_adapter
        setData()


        return  view
    }
    fun setData(){
        projectRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                arrayList = snapshot.getValue(ContentData::class.java)!!.list
                projectList.adapter = context?.let { FieldAdapter(it, arrayList) }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}