package com.example.liushengquan.douban.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.liushengquan.douban.R
import com.example.liushengquan.douban.base.BaseRecycleViewAdapter
import com.example.liushengquan.douban.util.DisplayImgUtis
import com.example.liushengquan.douban.view.impl.film.FilmDetailFragment

/**
 * Created by liushengquan on 2018/1/20.
 */
class FilmPersonAdapter: BaseRecycleViewAdapter<FilmDetailFragment.FilmPerson>() {

    override fun onCreateItemViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_film_detail_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindItemViewHolder(viewHolder: RecyclerView.ViewHolder?, position: Int, data: FilmDetailFragment.FilmPerson) {
        val personHolder = viewHolder as PersonViewHolder
        if (!TextUtils.isEmpty(data.img))
            DisplayImgUtis.display(mContext, data.img!!, personHolder.mPersonPhoto)
        personHolder.mlPersonName.text = data.name
        personHolder.mPersonType.text = data.type
    }

    override fun hasHeaderView(): Boolean {
        return false
    }

    override fun hasFooterView(): Boolean {
        return false
    }

    override fun onCreateHeaderViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateFooterViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindHeaderViewHolder(viewHolder: RecyclerView.ViewHolder?) {
    }

    override fun onBindFooterViewHolder(viewHolder: RecyclerView.ViewHolder?) {
    }

    inner class PersonViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val mPersonPhoto = rootView.findViewById<ImageView>(R.id.iv_film_detail_person_photo)!!
        val mlPersonName = rootView.findViewById<TextView>(R.id.tv_film_detail_person_name)!!
        val mPersonType = rootView.findViewById<TextView>(R.id.tv_film_detail_person_avatar)!!
    }
}