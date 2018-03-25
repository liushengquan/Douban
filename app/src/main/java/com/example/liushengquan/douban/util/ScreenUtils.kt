package com.example.liushengquan.douban.util

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * Created by liushengquan on 2018/1/14.
 */
class ScreenUtils {
    companion object {

        /**
         * 获取屏幕的宽度
         * @param context
         * @return
         */
        fun getScreenWidthPixels(context: Context):Int{
            var dm = DisplayMetrics()
            (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(dm)
            return dm.widthPixels
        }

        /**
         * 获取屏幕的宽度
         * @param context
         * @return
         */
        fun getScreenWidthDp(context: Context):Int{
            var width = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.width
            return width
        }

        /**
         * sp转px.
         *
         * @param value   the value
         * @param context the context
         * @return the int
         */
        fun sp2px(value: Float, context: Context?): Int {
            val r: Resources
            if (context == null) {
                r = Resources.getSystem()
            } else {
                r = context.resources
            }
            val spvalue = value * r.displayMetrics.scaledDensity
            return (spvalue + 0.5f).toInt()
        }

        /**
         * px转sp.
         *
         * @param value   the value
         * @param context the context
         * @return the int
         */
        fun px2sp(value: Float, context: Context): Int {
            val scale = context.resources.displayMetrics.scaledDensity
            return (value / scale + 0.5f).toInt()
        }

        /**
         * 获取屏幕密度
         * @param context
         * @return
         */

        fun getScreenDensity(context: Context): Float {
            try {
                val dm = DisplayMetrics()
                (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
                        .getMetrics(dm)
                return dm.density
            } catch (e: Exception) {
                return DisplayMetrics.DENSITY_DEFAULT.toFloat()
            }

        }

        /**
         * 获取屏幕宽高乘积
         * @param activity
         * @return
         */

        fun getScreenSize(activity: Activity): Int {
            var widthPixels = 0
            var heightPixels = 0
            val dm = DisplayMetrics()
            if (dm != null) {
                activity.windowManager.defaultDisplay.getMetrics(dm)
                widthPixels = dm.widthPixels
                heightPixels = dm.heightPixels
            }
            return widthPixels * heightPixels
        }


        /**
         * 获取屏幕的高度
         *
         * @param context
         * @return
         */
        fun getScreenHeight(context: Context): Int {
            val manager = context
                    .getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = manager.defaultDisplay
            return display.height
        }

        /**
         * dip 转px
         * @param context
         * @param dip
         * @return
         */
        fun dipToPx(context: Context, dip: Int): Int {
            return (dip * getScreenDensity(context) + 0.5f).toInt()
        }
    }
}