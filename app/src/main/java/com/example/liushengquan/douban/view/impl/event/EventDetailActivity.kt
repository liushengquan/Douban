package com.example.liushengquan.douban.view.impl.event

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import com.example.liushengquan.douban.R
import com.example.liushengquan.douban.base.BaseActivity
import com.example.liushengquan.douban.bean.Constant
import com.example.liushengquan.douban.util.AnimationUtils
import kotlinx.android.synthetic.main.toolbar_base.*

/**
 * Created by liushengquan on 2018/3/15.
 */
class EventDetailActivity: BaseActivity()  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        initView()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            AnimationUtils.setupWindowEnterAnimations(window)
    }

    override fun initView() {
        var title = intent.getStringExtra(Constant.EVENT_TITLE)
        //init toolbar
        setSupportActionBar(tb_base)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        tb_base.setNavigationOnClickListener({
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                finishAfterTransition()
            else
                finish()
        })

        cltl_base.title = title
        //通过CollapsingToolbarLayout修改字体颜色
        cltl_base.setExpandedTitleColor(Color.WHITE)    //设置还没收缩时状态下字体颜色
        cltl_base.setCollapsedTitleTextColor(Color.WHITE)   //设置收缩后Toolbar上字体的颜色

        var id = intent.getStringExtra(Constant.EVENT_ID)
        var fragment = EventDetailFragment.newInstance(id)
        addFragment(fragment, R.id.fl_base_content)
    }

    override fun initData() {
    }
}