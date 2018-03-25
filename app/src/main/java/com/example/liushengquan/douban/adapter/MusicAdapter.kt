package com.example.liushengquan.douban.adapter

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.liushengquan.douban.R
import com.example.liushengquan.douban.base.BaseRecycleViewAdapter
import com.example.liushengquan.douban.util.DisplayImgUtis
import com.example.liushengquan.douban.util.ScreenUtils
import com.example.liushengquan.douban.view.impl.music.MusicReadFragment

/**
 * Created by liushengquan on 2018/3/14.
 */
class MusicAdapter: BaseRecycleViewAdapter<MusicReadFragment.MusicData>()  {

    var mStatus = 1
    val LOAD_MORE = 0
    val LOAD_PULL_TO = 1
    val LOAD_NONE = 2
    val LOAD_END = 3

    override fun hasHeaderView(): Boolean {
        return false
    }

    override fun hasFooterView(): Boolean {
        return true
    }

    override fun onCreateHeaderViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateFooterViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_linear_footer, parent, false)
        return MusicFooterViewHolder(view)
    }

    override fun onCreateItemViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_music, parent, false)
        return MusicViewHolder(view)
    }

    override fun onBindItemViewHolder(viewHolder: RecyclerView.ViewHolder?, position: Int, data: MusicReadFragment.MusicData) {
        val musicHolder = viewHolder as MusicViewHolder
        if (!TextUtils.isEmpty(data.img))
            DisplayImgUtis.display(mContext, data.img!!, musicHolder.music_photo)
        musicHolder.music_title.text = data.title
        musicHolder.music_grade.text = data.grade
        musicHolder.music_actor.text = data.actor
    }


    override fun onBindHeaderViewHolder(viewHolder: RecyclerView.ViewHolder?) {
    }

    override fun onBindFooterViewHolder(viewHolder: RecyclerView.ViewHolder?) {
        val footerHolder = viewHolder as MusicFooterViewHolder

        var layoutParams = footerHolder.itemView.layoutParams
        val height = ScreenUtils.dipToPx(mContext, 80)
        layoutParams.height = height
        footerHolder.itemView.layoutParams = layoutParams

        when (mStatus) {
            LOAD_MORE -> {
                footerHolder.progressbar.visibility = View.VISIBLE
                footerHolder.tips.text = "正在加载..."
                footerHolder.itemView.visibility = View.VISIBLE
            }
            LOAD_PULL_TO -> {
                footerHolder.progressbar.visibility = View.GONE
                footerHolder.tips.text = "上拉加载更多"
                footerHolder.itemView.visibility = View.VISIBLE
            }
            LOAD_NONE -> {
                footerHolder.progressbar.visibility = View.GONE
                footerHolder.tips.text = "已无更多加载"
                footerHolder.itemView.visibility = View.VISIBLE
            }
            LOAD_END -> {
                footerHolder.itemView.visibility = View.GONE
            }
        }
    }

    fun updateState(status: Int){
        mStatus = status
        notifyDataSetChanged()
    }

    inner class MusicViewHolder(rootView: View):RecyclerView.ViewHolder(rootView){
        val music_photo = rootView.findViewById<ImageView>(R.id.iv_music_photo)!!
        val music_title = rootView.findViewById<TextView>(R.id.tv_music_title)!!
        val music_grade = rootView.findViewById<TextView>(R.id.tv_music_grade)!!
        val music_actor = rootView.findViewById<TextView>(R.id.tv_music_actor)!!
    }

    inner class MusicFooterViewHolder(rootView: View):RecyclerView.ViewHolder(rootView){
        val progressbar = rootView.findViewById<ProgressBar>(R.id.progressBar_footer)!!
        val tips = rootView.findViewById<TextView>(R.id.tv_footer_load_more)!!
    }

}