package com.example.liushengquan.douban.view.impl.film

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Explode
import android.transition.Fade
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.liushengquan.douban.R
import com.example.liushengquan.douban.base.BaseFragment
import com.example.liushengquan.douban.bean.Constant
import com.example.liushengquan.douban.bean.film.filmusbox.FilmUsBox
import com.example.liushengquan.douban.bean.film.top250.FilmTop
import com.example.liushengquan.douban.bean.filmlive.FilmLive
import com.example.liushengquan.douban.model.DatabaseRepertory
import com.example.liushengquan.douban.model.DoubanRepertory
import com.example.liushengquan.douban.presenter.FilmPresenter
import com.example.liushengquan.douban.view.interf.OnItemClickListener
import com.example.liushengquan.douban.view.interf.OnLoadMoreCallbak
import com.example.liushengquan.douban.view.interf.film.IShowFilmLive
import com.example.liushengquan.douban.view.interf.film.IShowTop250
import com.example.liushengquan.douban.view.interf.film.IShowUsBox
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


/**
 * Created by liushengquan on 2018/1/13.
 */
class FilmFragment: BaseFragment(),IShowFilmLive,IShowUsBox,IShowTop250,OnLoadMoreCallbak,OnItemClickListener<FilmFragment.FilmData> {

    val SIZE = 20
    var mCount = 0

    lateinit var mRepertory: DoubanRepertory
    lateinit var mFilmPresenter : FilmPresenter
    lateinit var mGridSectionAdapter: SectionedRecyclerViewAdapter
    lateinit var mTop250Session: GridSection
    lateinit var mFilmLiveSession: GridSection
    lateinit var mUsBoxSession: GridSection
    lateinit var mRootView: View
    lateinit var mRecyclerView: RecyclerView

    var mExecutor: ExecutorService = Executors.newCachedThreadPool()

    companion object {
        fun newInstance(): FilmFragment {
            var fragment = FilmFragment()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRepertory = DoubanRepertory(DatabaseRepertory())
        mFilmPresenter = FilmPresenter(mRepertory)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            setupWindowAnimations()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setupWindowAnimations() {
        val fade = Fade()
        fade.duration = 1000
        activity.window.exitTransition = fade

        val explode = Explode()
        explode.duration = 1000
        explode.excludeTarget(android.R.id.statusBarBackground,true)
        activity.window.reenterTransition = explode
        activity.window.allowEnterTransitionOverlap = false
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater!!.inflate(R.layout.fragment_film, container, false)
        initView()
        initData()
        return mRootView
    }

    override fun initView() {
        //初始化
        mRecyclerView = mRootView.findViewById(R.id.rv_film)
        mGridSectionAdapter = SectionedRecyclerViewAdapter()
        val glm = GridLayoutManager(mContext,3)
        glm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                when (mGridSectionAdapter.getSectionItemViewType(position)) {
                    SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER -> return 3
                    else -> return 1
                }
            }
        }
        mRecyclerView.layoutManager = glm
        mRecyclerView.adapter = mGridSectionAdapter

    }

    override fun initData() {
        mFilmPresenter.getFilmLive(this)
        mFilmPresenter.getFilmTop250(this,mCount,mCount+SIZE,false)
        mFilmPresenter.getFilmUsBox(this)
    }

    override fun isActive(): Boolean {
        return isAdded
    }

    override fun showTop250(filmTop: FilmTop, isLoadMore: Boolean) {
        val top250List = mutableListOf<FilmData>()
        for (i in filmTop.subjects!!.indices) {
            var filmData = FilmData(filmTop.subjects!![i].id,filmTop.subjects!![i].title, filmTop.subjects!![i].images!!.large, filmTop.subjects!![i].rating!!.average.toString())
            top250List.add(filmData)
        }
        if(!isLoadMore) {
            mTop250Session = GridSection(mContext, 3, top250List)
            mTop250Session.setOnLoadMoreCallbak(this)
            mTop250Session.setOnItemClickListener(this)
            mGridSectionAdapter.addSection(mTop250Session)
        }
        else
            mTop250Session.addDatas(top250List)
        mGridSectionAdapter.notifyDataSetChanged()
    }

    override fun showFilmLive(filmLive: FilmLive) {
        val filmLiveList = mutableListOf<FilmData>()
        for (i in filmLive.subjects!!.indices) {
            var filmData = FilmData(filmLive.subjects!![i].id,filmLive.subjects!![i].title, filmLive.subjects!![i].images!!.large, filmLive.subjects!![i].rating!!.average.toString())
            filmLiveList.add(filmData)
        }
        mFilmLiveSession = GridSection(mContext,0,filmLiveList)
        mFilmLiveSession.setOnLoadMoreCallbak(this)
        mFilmLiveSession.setOnItemClickListener(this)
        mGridSectionAdapter.addSection(mFilmLiveSession)
        mGridSectionAdapter.notifyDataSetChanged()
    }

    override fun showFilmUsBox(filmUsBox: FilmUsBox) {
        val usBoxList = mutableListOf<FilmData>()
        for (i in filmUsBox.subjects!!.indices) {
            var filmData = FilmData(filmUsBox.subjects!![i].subject!!.id,filmUsBox.subjects!![i].subject!!.title, filmUsBox.subjects!![i].subject!!.images!!.large, filmUsBox.subjects!![i].subject!!.rating!!.average.toString())
            usBoxList.add(filmData)
        }
        mUsBoxSession = GridSection(mContext,2,usBoxList)
        mUsBoxSession.setOnLoadMoreCallbak(this)
        mUsBoxSession.setOnItemClickListener(this)
        mGridSectionAdapter.addSection(mUsBoxSession)
        mGridSectionAdapter.notifyDataSetChanged()
    }

    override fun loadMore(type: Int) {
        //模拟加载过程
        showProgress("")
        mExecutor.execute {
            Thread.sleep(1000)
            activity.runOnUiThread {
               hideProgress()
            }
        }
        when (type) {
            0 -> if (!mFilmLiveSession.isLoadMore()) {
                mFilmLiveSession.setLoadMore(true)
                mGridSectionAdapter.notifyDataSetChanged()
            } else
                showToast("没有更多了")
            1 -> if (!mFilmLiveSession.isLoadMore()) {
                mFilmLiveSession.setLoadMore(true)
                mGridSectionAdapter.notifyDataSetChanged()
            } else
                showToast("没有更多了")
            2 -> if (!mUsBoxSession.isLoadMore()) {
                mUsBoxSession.setLoadMore(true)
                mGridSectionAdapter.notifyDataSetChanged()
            } else
                showToast("没有更多了")
            3 -> if (!mTop250Session.isLoadMore()) {
                mTop250Session.setLoadMore(true)
                mGridSectionAdapter.notifyDataSetChanged()
            } else {
                mCount+=SIZE
                mFilmPresenter.getFilmTop250(this, mCount, mCount + SIZE, true)
            }
        }
    }

    override fun OnItemClick(view: View, position: Int, data: FilmData) {
        var intent = Intent()
        intent.setClass(activity,FilmDetailActivity::class.java)
        intent.putExtra(Constant.FILM_ID, data.id)
        intent.putExtra(Constant.FILM_TITLE, data.name)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val options = ActivityOptions.makeSceneTransitionAnimation(activity, view.findViewById<ImageView>(R.id.iv_film_grid_photo), "film_image")
            startActivity(intent, options.toBundle())
        } else {
            startActivityByIntent(intent)
        }
    }

    override fun showFilmLiveFail(message: String) {
        showToast(message)
    }

    override fun showFilmUsBoxFail(message: String) {
        showToast(message)
    }

    override fun showTop250Fail(message: String) {
        showToast(message)
    }

    override fun showOrHideIndecator(visible: Boolean) {
    }


    data class FilmData(var id:String?,var name:String?,var img: String?, var grade: String?)
}