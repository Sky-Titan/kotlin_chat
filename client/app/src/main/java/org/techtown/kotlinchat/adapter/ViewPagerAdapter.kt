package org.techtown.kotlinchat.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

class ViewPagerAdapter: FragmentPagerAdapter {

    constructor(manager: FragmentManager) : super(manager)
    {

    }
    private val mFragmentList: MutableList<Fragment> = ArrayList<Fragment>()


    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {

        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)
    }
}