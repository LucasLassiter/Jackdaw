package com.example.jackdaw.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.jackdaw.fragments.MusicHolderFragment
import com.example.jackdaw.fragments.PlayListFragment
import com.example.jackdaw.fragments.SearchFragment

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