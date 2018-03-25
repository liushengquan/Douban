package com.example.liushengquan.douban

import android.app.Application
import android.content.Context

/**
 * Created by liushengquan on 2017/12/26.
 */
class DoubanApp : Application() {

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }

    companion object {
        lateinit var mContext: Context
        fun getAppContext() = if (mContext != null) mContext else DoubanApp().applicationContext
    }
}