package com.example.liushengquan.douban.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by liushengquan on 2018/1/6.
 */
class NetUtils {
    companion object {

        /**
         * 检查是否有网络
         * @param context
         * @return
         */
        fun checkNetWorkIsAvailable(context: Context): Boolean {
            val cwjManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cwjManager.activeNetworkInfo != null
        }

        /**
         * Wifi网络是否已连接
         *
         * @param context
         * @return
         */
        fun isNetworkConnectedByWifi(context: Context): Boolean {
            val networkInfo = (context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            return networkInfo != null && networkInfo.isConnected
        }

        /**
         * 网络是否已连接
         *
         * @param context
         * @return
         */
        fun isNetworkConnected(context: Context): Boolean {
            try {
                val networkInfo = (context
                        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                        .activeNetworkInfo
                return networkInfo != null && networkInfo.isConnected
            } catch (e: NullPointerException) {
                e.printStackTrace()
                return false
            }

        }
    }
}