package com.example.liushengquan.douban.view.impl.book

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import com.example.liushengquan.douban.R
import com.example.liushengquan.douban.adapter.BookViewpagerAdapter
import com.example.liushengquan.douban.api.BookApiUtils
import com.example.liushengquan.douban.base.BaseFragment
import com.example.liushengquan.douban.bean.book.Books
import com.example.liushengquan.douban.model.DatabaseRepertory
import com.example.liushengquan.douban.model.DoubanRepertory
import com.example.liushengquan.douban.presenter.BookPresenter
import com.example.liushengquan.douban.view.interf.book.IShowBooks

/**
 * Created by liushengquan on 2018/1/13.
 */
class BookFragment: BaseFragment(),ViewPager.OnPageChangeListener{

    lateinit var vp_book: ViewPager
    lateinit var tl_book: TabLayout
    lateinit var mRootView: View

    // TabLayout中的tab标题
    lateinit var mTitles: Array<String>
    lateinit var mViewPagerAdapter: BookViewpagerAdapter

    companion object {
        fun newInstance():BookFragment{
            var fragment = BookFragment()
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater!!.inflate(R.layout.fragment_book,container,false)
        initView()
        initData()
        return mRootView
    }

    override fun initView() {
        tl_book = mRootView.findViewById(R.id.tl_book)
        vp_book = mRootView.findViewById(R.id.vp_book)

        mTitles = BookApiUtils.Tag_Titles
        mViewPagerAdapter = BookViewpagerAdapter(childFragmentManager, mTitles)
        vp_book.adapter = mViewPagerAdapter
        vp_book.offscreenPageLimit = 4
        vp_book.addOnPageChangeListener(this)

        // 将TabLayout和ViewPager进行关联，让两者联动起来
        tl_book.setupWithViewPager(vp_book)
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