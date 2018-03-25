package com.example.liushengquan.douban.view.impl.film

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.liushengquan.douban.R
import com.example.liushengquan.douban.util.DisplayImgUtis
import com.example.liushengquan.douban.util.ScreenUtils
import com.example.liushengquan.douban.view.interf.OnItemClickListener
import com.example.liushengquan.douban.view.interf.OnLoadMoreCallbak
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection

/**
 * Created by liushengquan on 2018/1/17.
 */
class GridSection (context: Context, type: Int, datas: MutableList<FilmFragment.FilmData>) : StatelessSection(SectionParameters.Builder(R.layout.item_film_grid_content)
        .headerResourceId(R.layout.item_film_grid_header)
        .build()) {

    var mloadMore = false
    var mType = type
    var mContext = context
    lateinit var mTitle: String
    var ivWidth: Int
    private val mDatas = datas
    var mLoadMoreCallback: OnLoadMoreCallbak? = null
    var mOnItemClickListener: OnItemClickListener<FilmFragment.FilmData>? = null

    init {
        var width = ScreenUtils.getScreenWidthDp(mContext)
        ivWidth = (width - ScreenUtils.dipToPx(mContext, 80)) / 3
        when (type) {
            0 -> mTitle = "正在热映"
            1 -> mTitle = "最新上映"
            2 -> mTitle = "北美排行"
            3 -> mTitle = "Top250"
        }
    }

    fun setOnLoadMoreCallbak(callback: OnLoadMoreCallbak) {
        mLoadMoreCallback = callback
    }

    fun setOnItemClickListener(listener: OnItemClickListener<FilmFragment.FilmData>){
        mOnItemClickListener = listener
    }

    fun isLoadMore(): Boolean{
        return mloadMore
    }

    fun setLoadMore(loadMore: Boolean){
        mloadMore = loadMore
    }

    fun addDatas(datas: MutableList<FilmFragment.FilmData>){
        mDatas.addAll(datas)
    }

    override fun getContentItemsTotal(): Int {
        if (mDatas == null || mDatas.size == 0)
            return 0
        if(!mloadMore)
            return 9
        return mDatas.size
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val itemHolder = holder as ItemViewHolder
        var data = mDatas[position]
        itemHolder.name.text = data.name
        var layoutParams = itemHolder.photo.layoutParams
        layoutParams.width = ivWidth
        val height = 420.0 / 300.0 * ivWidth
        layoutParams.height = height.toInt()
        itemHolder.photo.layoutParams = layoutParams

        if (!TextUtils.isEmpty(data.img))
            DisplayImgUtis.display(mContext, data.img!!, itemHolder.photo)
        if (!TextUtils.isEmpty(data.grade))
            itemHolder.grade.text = "评分:" + data.grade
        else
            itemHolder.grade.text = "暂无评分"

        itemHolder.root.setOnClickListener {
            if(mOnItemClickListener!=null)
                mOnItemClickListener!!.OnItemClick(itemHolder.root,position,data)
        }

    }

    override fun getItemViewHolder(view: View?): RecyclerView.ViewHolder {
        return ItemViewHolder(view!!)
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?) {
        val headHolder = holder as HeaderViewHolder
        headHolder.title.text = mTitle
        headHolder.more.setOnClickListener {
            if (mLoadMoreCallback != null)
                mLoadMoreCallback!!.loadMore(mType)
        }
    }

    override fun getHeaderViewHolder(view: View?): RecyclerView.ViewHolder {
        return HeaderViewHolder(view!!)
    }

    inner class HeaderViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val title = rootView?.findViewById<TextView>(R.id.tv_film_grid_title)!!
        val more = rootView?.findViewById<Button>(R.id.btn_film_grid_more)!!
    }


    inner class ItemViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val root = rootView
        val photo: ImageView = rootView?.findViewById<ImageView>(R.id.iv_film_grid_photo) as ImageView
        val name: TextView = rootView?.findViewById<TextView>(R.id.tv_film_grid_name) as TextView
        val grade: TextView = rootView?.findViewById<TextView>(R.id.tv_film_grid_grade) as TextView
    }
}