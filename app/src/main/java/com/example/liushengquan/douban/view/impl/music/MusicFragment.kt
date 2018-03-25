package com.example.liushengquan.douban.view.impl.music

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.liushengquan.douban.R
import com.example.liushengquan.douban.adapter.MusicViewpagerAdapter
import com.example.liushengquan.douban.api.MusicApiUtils
import com.example.liushengquan.douban.base.BaseFragment
import com.example.liushengquan.douban.bean.music.Musics
import com.example.liushengquan.douban.view.interf.music.IShowMusics

/**
 * Created by liushengquan on 2018/1/13.
 */
class MusicFragment: BaseFragment(),ViewPager.OnPageChangeListener {

    lateinit var vp_music: ViewPager
    lateinit var tl_music: TabLayout
    lateinit var mRootView: View

    lateinit var mTitles: Array<String>
    lateinit var mViewPagerAdapter: MusicViewpagerAdapter

    companion object {
        fun newInstance():MusicFragment{
            var fragment = MusicFragment()
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater!!.inflate(R.layout.fragment_music,container,false)
        initView()
        initData()
        return mRootView
    }

    override fun initView() {
        tl_music = mRootView.findViewById(R.id.tl_music)
        vp_music = mRootView.findViewById(R.id.vp_music)

        mTitles = MusicApiUtils.Music_Titles
        mViewPagerAdapter = MusicViewpagerAdapter(childFragmentManager, mTitles)
        vp_music.adapter = mViewPagerAdapter
        vp_music.offscreenPageLimit = 4
        vp_music.addOnPageChangeListener(this)

        // 将TabLayout和ViewPager进行关联，让两者联动起来
        tl_music.setupWithViewPager(vp_music)
    }

    override fun initData() {

    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
    }

}