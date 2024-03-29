package com.feechan.footballapps.component.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class FavoritesPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm){
    private val mFragmentList: MutableList<Fragment> = mutableListOf();

    override fun getCount(): Int  = mFragmentList.size

    override fun getItem(i: Int): Fragment {
        return mFragmentList[i]
    }

    fun addFragment(fragment: Fragment){
        mFragmentList.add(fragment)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "MATCHES"
            else -> "TEAMS"
        }
    }
}