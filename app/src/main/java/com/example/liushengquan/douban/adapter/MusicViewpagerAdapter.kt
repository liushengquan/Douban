package com.example.liushengquan.douban.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.liushengquan.douban.view.impl.music.MusicReadFragment

/**
 * Created by liushengquan on 2018/3/14.
 */
class MusicViewpagerAdapter (fm: FragmentManager, titles: Array<String>): FragmentStatePagerAdapter(fm) {
    var mTitles = titles
    override fun getItem(position: Int): Fragment {
        return MusicReadFragment.newInstance(mTitles[position])
    }

    override fun getCount(): Int {
        return mTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTitles[position]
    }
}