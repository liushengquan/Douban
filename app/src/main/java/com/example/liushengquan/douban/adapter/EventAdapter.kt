package com.example.liushengquan.douban.adapter

import android.content.Context
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
import com.example.liushengquan.douban.view.impl.event.EventFragment

/**
 * Created by liushengquan on 2018/3/15.
 */
class EventAdapter: BaseRecycleViewAdapter<EventFragment.EventData>() {

    var ivWidth = 0
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

    override fun onBindHeaderViewHolder(viewHolder: RecyclerView.ViewHolder?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateItemViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        ivWidth = (mWidth - ScreenUtils.dipToPx(mContext, 80)) / 3
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onCreateFooterViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_grid_footer, parent, false)
        return EventFooterViewHolder(view)
    }

    override fun onBindItemViewHolder(viewHolder: RecyclerView.ViewHolder?, position: Int, data: EventFragment.EventData) {
        val eventHolder = viewHolder as EventViewHolder

        var layoutParams = eventHolder.event_photo.layoutParams
        layoutParams.width = ivWidth
        val height = 420.0 / 300.0 * ivWidth
        layoutParams.height = height.toInt()
        eventHolder.event_photo.layoutParams = layoutParams

        if (!TextUtils.isEmpty(data.img))
            DisplayImgUtis.display(mContext, data.img!!, eventHolder.event_photo)
        eventHolder.event_title.text = data.title
        eventHolder.event_date.text = data.date
    }

    override fun onBindFooterViewHolder(viewHolder: RecyclerView.ViewHolder?) {
        val footerHolder = viewHolder as EventFooterViewHolder
        var layoutParams = footerHolder.itemView.layoutParams
        layoutParams.width = ivWidth
        val height = 420.0 / 300.0 * ivWidth
        layoutParams.height = height.toInt()
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

    inner class EventViewHolder(rootView: View):RecyclerView.ViewHolder(rootView){
        val event_photo = rootView.findViewById<ImageView>(R.id.iv_event_photo)!!
        val event_title = rootView.findViewById<TextView>(R.id.tv_event_title)!!
        val event_date = rootView.findViewById<TextView>(R.id.tv_event_date)!!
    }

    inner class EventFooterViewHolder(rootView: View):RecyclerView.ViewHolder(rootView){
        val progressbar = rootView.findViewById<ProgressBar>(R.id.progressBar_footer)!!
        val tips = rootView.findViewById<TextView>(R.id.tv_footer_load_more)!!
    }
}