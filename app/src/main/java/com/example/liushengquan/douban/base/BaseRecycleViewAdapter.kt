package com.example.liushengquan.douban.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.liushengquan.douban.util.ScreenUtils
import com.example.liushengquan.douban.view.interf.OnItemClickListener
import com.example.liushengquan.douban.view.interf.OnItemLongClickListener

/**
 * Created by liushengquan on 2018/1/7.
 */
abstract class BaseRecycleViewAdapter<T>(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TYPE_HEAD = 0
    val TYPE_BODY = 1
    val TYPE_FOOT = 2


    var mDatas = mutableListOf<T>()
    var mWidth = 0
    lateinit var mContext:Context

    var mOnItemClickListener: OnItemClickListener<T>? = null
    var mOnItemLongClickListener: OnItemLongClickListener<T>? = null

    constructor(datas: MutableList<T>):this(){
        mDatas = datas
    }

    fun setDatas(datas: MutableList<T>){
        mDatas = datas
    }

    fun addDatas(datas: MutableList<T>){
        if(mDatas!=null)
            mDatas.addAll(datas)
    }

    fun getDatas():MutableList<T> {
        return mDatas
    }

    fun remove(item: T){
        mDatas.remove(item)
    }

    fun setOnItemClickListener(listener:OnItemClickListener<T>){
        mOnItemClickListener = listener
    }

    fun setOnItemLongClickListener(listener:OnItemLongClickListener<T>){
        mOnItemLongClickListener = listener
    }

    abstract fun hasHeaderView():Boolean
    abstract fun hasFooterView():Boolean

    abstract fun onCreateItemViewHolder(parent: ViewGroup?,viewType: Int):RecyclerView.ViewHolder

    abstract fun onCreateHeaderViewHolder(parent: ViewGroup?,viewType: Int):RecyclerView.ViewHolder

    abstract fun onCreateFooterViewHolder(parent: ViewGroup?,viewType: Int):RecyclerView.ViewHolder

    abstract fun onBindItemViewHolder(viewHolder: RecyclerView.ViewHolder?, position: Int, data: T)

    abstract fun onBindHeaderViewHolder(viewHolder: RecyclerView.ViewHolder?)

    abstract fun onBindFooterViewHolder(viewHolder: RecyclerView.ViewHolder?)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if(hasHeaderView()&&position==0){
            onBindHeaderViewHolder(holder)
            return
        }
        if((hasFooterView()&&position==mDatas.size)||(hasHeaderView()&&hasFooterView()&&position==mDatas.size+1)){
            onBindFooterViewHolder(holder)
            return
        }

        val data = mDatas[position]
        onBindItemViewHolder(holder, position, data)

        if (mOnItemClickListener != null) {
            holder!!.itemView.setOnClickListener { v ->
                    mOnItemClickListener!!.OnItemClick(v, position, data)
            }
        }

        if (mOnItemLongClickListener != null) {
            holder!!.itemView.setOnLongClickListener { v ->
                mOnItemLongClickListener!!.OnItemLongClick(v, position, data)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        //计算屏幕大小
        mContext = parent!!.context
        mWidth = ScreenUtils.getScreenWidthDp(mContext)
        when(viewType){
            TYPE_HEAD -> return onCreateHeaderViewHolder(parent,viewType)
            TYPE_FOOT ->return onCreateFooterViewHolder(parent,viewType)
            else ->
                return onCreateItemViewHolder(parent,viewType)
        }
    }

    override fun getItemCount(): Int {
        if (hasHeaderView() && hasFooterView()) {
            return mDatas.size + 2
        } else if (hasHeaderView() ||hasFooterView()) {
            return mDatas.size + 1
        }
        return mDatas.size
    }

    override fun getItemViewType(position: Int): Int {
        if (!hasHeaderView()&&!hasFooterView()) {
            return TYPE_BODY
        }
        if (hasHeaderView() && position == 0) {
            return TYPE_HEAD
        }
        if (hasFooterView() && position == mDatas.size) {
            return TYPE_FOOT
        }
        return if (hasHeaderView() && hasFooterView() && position == mDatas.size + 1) {
            TYPE_FOOT
        } else TYPE_BODY
    }

}