package com.example.hackathon.Fragment.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.hackathon.Fragment.Field.CompetitionFragment
import com.example.hackathon.Fragment.Field.ProjectFragment
import com.example.hackathon.Fragment.Field.PortfolioFragment
import com.example.hackathon.Fragment.Field.Self_introductionFragment

@Suppress("DEPRECATION")
class FragmentAdapter (fm : FragmentManager) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> {
                ProjectFragment()
            }
            1 -> {
                CompetitionFragment()
            }
            2 -> {
                PortfolioFragment()
            }
            else ->{
                Self_introductionFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 4
    }

}