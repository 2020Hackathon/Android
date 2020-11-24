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
import kotlinx.android.synthetic.main.fragment_portfolio.*
import kotlinx.android.synthetic.main.fragment_portfolio.view.*

class PortfolioFragment : Fragment() {
    var database = FirebaseDatabase.getInstance()
    val portfolioRef = database.getReference("protpolio")
    var arrayList = ArrayList<ContentListModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_portfolio, container, false)
        val items = resources.getStringArray(R.array.field_array)

        var field_spinner_adapter =
            context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, items) }
        view.port_category.adapter = field_spinner_adapter
        setData()

        return view


    }
    fun setData(){
        portfolioRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                arrayList = snapshot.getValue(ContentData::class.java)!!.list
                portfolioList.adapter = context?.let { FieldAdapter(it, arrayList) }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}