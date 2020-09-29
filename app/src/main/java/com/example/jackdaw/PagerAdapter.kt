package com.example.jackdaw

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fm: FragmentManager, numOfTabs: Int) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var numOfTabs: Int = numOfTabs

    override fun getCount(): Int {
        return numOfTabs
    }

    override fun getItem(position: Int): Fragment {
        when(position)
        {
            0 -> return  PlayListFragment()
            1 -> return MusicFragment()
            2 -> return SearchFragment()
            else -> return null!!
        }
    }
}