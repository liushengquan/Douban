package com.example.liushengquan.douban.view.impl.music

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.liushengquan.douban.R
import com.example.liushengquan.douban.adapter.MusicAdapter
import com.example.liushengquan.douban.base.BaseFragment
import com.example.liushengquan.douban.bean.Constant
import com.example.liushengquan.douban.bean.music.Musics
import com.example.liushengquan.douban.model.DatabaseRepertory
import com.example.liushengquan.douban.model.DoubanRepertory
import com.example.liushengquan.douban.presenter.MusicPresenter
import com.example.liushengquan.douban.util.AnimationUtils
import com.example.liushengquan.douban.view.interf.OnItemClickListener
import com.example.liushengquan.douban.view.interf.music.IShowMusics
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by liushengquan on 2018/3/14.
 */
class MusicReadFragment : BaseFragment(),IShowMusics, OnItemClickListener<MusicReadFragment.MusicData> {

    lateinit var rv_music: RecyclerView
    lateinit var refresh_music: SwipeRefreshLayout
    lateinit var mRootView: View

    lateinit var mRepertory: DoubanRepertory
    lateinit var mMusicPresenter: MusicPresenter
    lateinit var mMusicAdapter: MusicAdapter
    lateinit var mLayoutManager: LinearLayoutManager

    lateinit var mTag: String
    val mMusics = mutableListOf<MusicData>()
    var mExecutor: ExecutorService = Executors.newCachedThreadPool()

    companion object {
        fun newInstance(tag: String): MusicReadFragment {
            var fragment = MusicReadFragment()
            var bundle = Bundle()
            bundle.putString(Constant.MUSIC_TAG,tag)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRepertory = DoubanRepertory(DatabaseRepertory())
        mMusicPresenter = MusicPresenter(mRepertory)
        mTag = arguments.getString(Constant.MUSIC_TAG)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            AnimationUtils.setupWindowExitAnimations(activity.window)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater!!.inflate(R.layout.fragment_music_read,container,false)
        initView()
        initData()
        return mRootView
    }

    override fun initView() {
        rv_music = mRootView.findViewById(R.id.rv_music_read)
        rv_music.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    var lastVisibleItem = mLayoutManager.findLastVisibleItemPosition()
                    if (mLayoutManager.itemCount == 1) {
                        if (mMusicAdapter != null) {
                            mMusicAdapter.updateState(mMusicAdapter.LOAD_NONE)
                        }
                        return
                    }
                    if (lastVisibleItem + 1 == mLayoutManager.itemCount) {
                        if (mMusicAdapter != null) {
                            mMusicPresenter!!.searchMusicByTag(this@MusicReadFragment,mTag,true)
                            mMusicAdapter.updateState(mMusicAdapter.LOAD_PULL_TO)
                            mMusicAdapter.updateState(mMusicAdapter.LOAD_MORE)
                        }
                    }
                }
            }
        })

        refresh_music = mRootView.findViewById(R.id.refresh_music_read)
        refresh_music.setOnRefreshListener {
            mExecutor.execute {
                Thread.sleep(1000)
                activity.runOnUiThread {
                    Toast.makeText(mContext,"已刷新", Toast.LENGTH_LONG).show()
                    refresh_music.isRefreshing = false
                }
            }
        }
        showOrHideIndecator(true)
    }

    override fun initData() {
        mMusicAdapter = MusicAdapter()
        mMusicAdapter.setOnItemClickListener(this)
        mLayoutManager = LinearLayoutManager(mContext)
        rv_music!!.layoutManager = mLayoutManager
        rv_music!!.adapter = mMusicAdapter

        if(!TextUtils.isEmpty(mTag))
            mMusicPresenter!!.searchMusicByTag(this,mTag,false)
    }

    override fun isActive(): Boolean {
        return isAdded
    }

    override fun showMusics(musics: Musics, isLoadMore: Boolean) {
        if(!isLoadMore)
            mMusics.clear()
        for(music in musics!!.musics!!){
            var musicData = MusicData(music!!.id,music!!.title,music!!.image,music!!.rating!!.average, music!!.author!![0].name)
            mMusics.add(musicData)
        }

        mMusicAdapter.setDatas(mMusics)
        mMusicAdapter.updateState(mMusicAdapter.LOAD_END)

        showOrHideIndecator(false)
    }

    override fun showMusicsFail(message: String) {
        showToast(message)
    }

    override fun showOrHideIndecator(visible: Boolean) {
        refresh_music.isRefreshing = visible
    }

    override fun OnItemClick(view: View, position: Int, data: MusicData) {
        var intent = Intent()
        intent.setClass(activity,MusicDetailActivity::class.java)
        intent!!.putExtra(Constant.MUSIC_ID,data!!.id)
        intent!!.putExtra(Constant.MUSIC_TITLE,data!!.title)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val options = ActivityOptions.makeSceneTransitionAnimation(activity, view.findViewById<ImageView>(R.id.iv_music_photo), "music_image")
            startActivity(intent, options.toBundle())
        } else {
            startActivityByIntent(intent)
        }
    }

    data class MusicData(var id:String?,var title:String?,var img: String?, var grade: String?,var actor: String?)
}