package com.example.liushengquan.douban.util

import android.os.Build
import android.support.annotation.RequiresApi
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import com.example.liushengquan.douban.R

/**
 * Created by liushengquan on 2018/4/6.
 */
object AnimationUtils {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setupWindowExitAnimations(window: Window) {
        val fade = Fade()
        fade.duration = 1000
        window.exitTransition = fade

        val explode = Explode()
        explode.duration = 1000
        explode.excludeTarget(android.R.id.statusBarBackground,true)
        explode.excludeTarget(android.R.id.navigationBarBackground,true)
        explode.excludeTarget(R.id.ll_main_actions,true)
        window.reenterTransition = explode
        window.allowEnterTransitionOverlap = false
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setupWindowEnterAnimations(window: Window) {
        val slide = Slide(Gravity.LEFT)
        slide.duration = 1000
        slide.excludeTarget(android.R.id.statusBarBackground,true)
        slide.excludeTarget(android.R.id.navigationBarBackground,true)
        slide.excludeTarget(R.id.abl_base,true)
        window.enterTransition = slide

        val fade = Fade()
        window.returnTransition = fade
        window.allowReturnTransitionOverlap = false
    }
}