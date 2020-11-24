package com.example.hackathon.DTO;

import com.example.hackathon.Fragment.ContentListModel;

import java.util.ArrayList;

public class ContentData {

    ArrayList<ContentListModel> list;



    public ContentData(ArrayList<ContentListModel> list){
        this.list = list;
    }

    public ContentData(){

    }

    public ArrayList<ContentListModel> getList() {
        return list;
    }

    public void setList(ArrayList<ContentListModel> list) {
        this.list = list;
    }
}
