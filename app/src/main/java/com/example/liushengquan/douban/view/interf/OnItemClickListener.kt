package com.example.liushengquan.douban.view.interf

import android.view.View

/**
 * Created by liushengquan on 2018/1/20.
 */
interface OnItemClickListener<T> {
    fun OnItemClick(view: View, position: Int, data: T)
}