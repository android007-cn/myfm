package com.yx.myfm.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @Description:
 * @Author:      jerry
 * @CreateDate:  2020-07-23 15:57
 * @Email:       309032663@qq.com
 */
class CommonFragmentPagerAdapter(fm: androidx.fragment.app.FragmentManager, titleList: MutableList<String>,
                                 fragmentList: MutableList<Fragment>) : FragmentPagerAdapter(fm) {
    private var titles: MutableList<String> = titleList
    private var fragments: MutableList<Fragment> = fragmentList

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}