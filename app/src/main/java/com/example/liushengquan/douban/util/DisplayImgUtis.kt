package com.example.liushengquan.douban.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by liushengquan on 2018/1/14.
 */
object DisplayImgUtis {

    fun display(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url).into(imageView)
    }
}