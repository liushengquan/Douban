package com.example.liushengquan.douban.view.impl.music

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.example.liushengquan.douban.R
import com.example.liushengquan.douban.base.BaseFragment
import com.example.liushengquan.douban.bean.Constant
import com.example.liushengquan.douban.bean.music.Music
import com.example.liushengquan.douban.model.DatabaseRepertory
import com.example.liushengquan.douban.model.DoubanRepertory
import com.example.liushengquan.douban.presenter.MusicPresenter
import com.example.liushengquan.douban.util.DisplayImgUtis
import com.example.liushengquan.douban.view.interf.music.IShowMusicDetail

/**
 * Created by liushengquan on 2018/3/14.
 */
class MusicDetailFragment : BaseFragment(), IShowMusicDetail {

    lateinit var mRootView: View
    lateinit var iv_photo: ImageView
    lateinit var tv_title: TextView
    lateinit var tv_author: TextView
    lateinit var tv_singer: TextView
    lateinit var tv_pubdate: TextView
    lateinit var tv_rating: TextView
    lateinit var tv_rating_person: TextView
    lateinit var tv_sunmery: TextView
    lateinit var tv_song: TextView
    lateinit var ratingbar: RatingBar


    lateinit var mRepertory: DoubanRepertory
    lateinit var mMusicPresenter: MusicPresenter

    lateinit var mId: String

    companion object {
        fun newInstance(id:String): MusicDetailFragment{
            var fragment = MusicDetailFragment()
            var bundle = Bundle()
            bundle.putString(Constant.MUSIC_ID,id)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRepertory = DoubanRepertory(DatabaseRepertory())
        mMusicPresenter = MusicPresenter(mRepertory)
        mId = arguments.getString(Constant.MUSIC_ID)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater!!.inflate(R.layout.fragment_music_detail,container,false)
        initView()
        initData()
        return mRootView
    }

    override fun initView() {
        iv_photo  = mRootView.findViewById(R.id.iv_music_detail_photo)
        tv_title =  mRootView.findViewById(R.id.tv_music_detail_title)
        tv_author =  mRootView.findViewById(R.id.tv_music_detail_author)
        tv_singer=  mRootView.findViewById(R.id.tv_music_detail_singer)
        tv_pubdate=  mRootView.findViewById(R.id.tv_music_detail_pubdate)
        tv_rating =  mRootView.findViewById(R.id.tv_music_detail_grade)
        tv_rating_person =  mRootView.findViewById(R.id.tv_music_detail_rating_person)
        tv_sunmery =  mRootView.findViewById(R.id.tv_music_detail_sunmery)
        tv_song =  mRootView.findViewById(R.id.tv_music_detail_song)
        ratingbar = mRootView.findViewById(R.id.rb_music_detail)
        ratingbar.stepSize = 0.5f
    }

    override fun isActive(): Boolean {
        return isAdded
    }

    override fun initData() {
        mMusicPresenter.getMusicById(this,mId)
    }

    override fun showMusicDetail(music: Music) {

        if (!TextUtils.isEmpty(music.image))
            DisplayImgUtis.display(mContext, music.image, iv_photo)
        tv_title.text = music.title
        if(music.attrs!=null) {
            tv_singer.text = "演唱：" + music.attrs!!.singer!![0]
            tv_pubdate.text = "出版日期：" + music.attrs!!.pubdate
            tv_song.text = music.attrs!!.tracks!![0]
        }
        tv_author.text = "作曲：" + music.author!![0].name
        tv_rating.text = music.rating!!.average
        tv_rating_person.text = music.rating!!.numRaters.toString() + "人"
        tv_sunmery.text = music.summary

        //计算星星
        var num = music.rating!!.average!!.toFloat() /2
        ratingbar.rating = num
    }

    override fun showMusicDetailFail(message: String) {
        showToast(message)
    }
}