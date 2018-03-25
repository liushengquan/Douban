package com.example.liushengquan.douban.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.liushengquan.douban.view.impl.book.BookReadFragment

/**
 * Created by liushengquan on 2018/1/21.
 */
class BookViewpagerAdapter(fm: FragmentManager, titles: Array<String>): FragmentStatePagerAdapter(fm) {
    var mTitles = titles
    override fun getItem(position: Int): Fragment {
        return BookReadFragment.newInstance(mTitles[position])
    }

    override fun getCount(): Int {
        return mTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTitles[position]
    }
}