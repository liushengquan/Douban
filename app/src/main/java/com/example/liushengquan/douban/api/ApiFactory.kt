package com.example.liushengquan.douban.api

import android.content.Context

/**
 * Created by liushengquan on 2018/1/6.
 * 单例类
 */
object ApiFactory {
    fun getDoubanApiService(context: Context): DoubanApi? {
        return ApiRetrofit.getDoubanApiService(context)
    }
}