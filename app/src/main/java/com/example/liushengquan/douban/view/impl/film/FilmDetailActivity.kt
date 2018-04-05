package com.example.liushengquan.douban.view.impl.film

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import com.example.liushengquan.douban.R
import com.example.liushengquan.douban.base.BaseActivity
import com.example.liushengquan.douban.bean.Constant
import kotlinx.android.synthetic.main.toolbar_base.*

/**
 * Created by liushengquan on 2018/1/20.
 */
class FilmDetailActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        initView()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            setupWindowAnimations()
    }

    @SuppressLint("NewApi")
    private fun setupWindowAnimations() {
        val slide = Slide(Gravity.LEFT)
        slide.duration = 1000
        slide.startDelay = 1000
        slide.excludeTarget(android.R.id.statusBarBackground,true)
        slide.excludeTarget(R.id.abl_base,true)
        window.enterTransition = slide

        val fade = Fade()
        window.returnTransition = fade
        window.allowReturnTransitionOverlap = false
    }

    override fun initView() {
        var title = intent.getStringExtra(Constant.FILM_TITLE)
        //init toolbar
        setSupportActionBar(tb_base)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
//        supportActionBar!!.setHomeAsUpIndicator(R.drawable.back)
        tb_base.setNavigationOnClickListener({
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                finishAfterTransition()
            else
                finish()
        })

        cltl_base.title = title
//        //通过CollapsingToolbarLayout修改字体颜色
        cltl_base.setExpandedTitleColor(Color.WHITE)    //设置还没收缩时状态下字体颜色
        cltl_base.setCollapsedTitleTextColor(Color.WHITE)   //设置收缩后Toolbar上字体的颜色

        var id = intent.getStringExtra(Constant.FILM_ID)
        var fragment = FilmDetailFragment.newInstance(id)
        addFragment(fragment,R.id.fl_base_content)
    }

    override fun initData() {
    }
}