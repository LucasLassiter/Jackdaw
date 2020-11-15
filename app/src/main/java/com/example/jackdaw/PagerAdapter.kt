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
        return when(position) {
            0 -> PlayListFragment()
            1 -> MusicHolderFragment()
            2 -> SearchFragment()
            else -> null!!
        }
    }
}