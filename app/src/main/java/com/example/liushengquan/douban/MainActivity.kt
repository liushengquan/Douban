package com.example.liushengquan.douban

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import com.example.liushengquan.douban.base.BaseActivity
import com.example.liushengquan.douban.view.impl.book.BookFragment
import com.example.liushengquan.douban.view.impl.event.EventFragment
import com.example.liushengquan.douban.view.impl.film.FilmFragment
import com.example.liushengquan.douban.view.impl.music.MusicFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),View.OnClickListener {

    lateinit var mFilmFragment: FilmFragment
    lateinit var mBookFragment: BookFragment
    lateinit var mEventFragment: EventFragment
    lateinit var mMusicFragment: MusicFragment
    val mListFragment = mutableListOf<Fragment>()

    var mCurrentFragment = -1

    var mAdapter = object : FragmentPagerAdapter(supportFragmentManager){
        override fun getItem(position: Int): Fragment {
            return mListFragment.get(position)
        }

        override fun getCount(): Int {
            return mListFragment.size
        }
    }

    private var mOnPageChangeListener = object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initViewpagerAndFragment()
    }

    override fun initView(){
        rl_main_film.setOnClickListener(this)
        rl_main_book.setOnClickListener(this)
        rl_main_music.setOnClickListener(this)
        rl_main_event.setOnClickListener(this)
    }

    override fun initData() {
    }

    fun initViewpagerAndFragment(){
        mFilmFragment = FilmFragment.newInstance()
        mBookFragment = BookFragment.newInstance()
        mMusicFragment = MusicFragment.newInstance()
        mEventFragment = EventFragment.newInstance()

        mListFragment.add(mFilmFragment as Fragment)
        mListFragment.add(mBookFragment as Fragment)
        mListFragment.add(mMusicFragment as Fragment)
        mListFragment.add(mEventFragment as Fragment)

        vp_main.adapter = mAdapter
        vp_main.offscreenPageLimit = 4
        vp_main.addOnPageChangeListener(mOnPageChangeListener)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.rl_main_film -> mCurrentFragment = 0
            R.id.rl_main_book -> mCurrentFragment = 1
            R.id.rl_main_music -> mCurrentFragment = 2
            R.id.rl_main_event -> mCurrentFragment = 3
        }
        vp_main.setCurrentItem(mCurrentFragment,false)
    }

}
