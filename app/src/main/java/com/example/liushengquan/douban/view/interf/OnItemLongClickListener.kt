package com.example.liushengquan.douban.view.interf

import android.view.View

/**
 * Created by liushengquan on 2018/1/20.
 */
interface OnItemLongClickListener<T> {
    fun OnItemLongClick(view: View, position: Int, data: T)
}