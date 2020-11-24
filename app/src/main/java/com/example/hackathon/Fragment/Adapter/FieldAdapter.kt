package com.example.hackathon.Fragment.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.hackathon.Fragment.ContentListModel
import com.example.hackathon.R

//class FieldAdapter (context : Context, dataList : List<ContentListModel>) : BaseAdapter(){
//    private val mContext : Context = context
//    private val fieldData = dataList
//
//    override fun getCount(): Int {
//        return  fieldData.size
//    }
//
//    override fun getItem(p0: Int): Any {
//        return fieldData[p0]
//    }
//
//    override fun getItemId(p0: Int): Long {
//        return p0.toLong()
//    }
//
//    @SuppressLint("ViewHolder")
//    override fun getView(p0: Int, p1: View?, p2: ViewGroup?) : View {
//        val layoutInflater = LayoutInflater.from(mContext)
//        val view = layoutInflater.inflate(R.layout.listview_item, p2, false)
//        val field_name = view.findViewById<TextView>(R.id.title)
//        field_name.text = fieldData[p0].title
//
//    }
//
//}