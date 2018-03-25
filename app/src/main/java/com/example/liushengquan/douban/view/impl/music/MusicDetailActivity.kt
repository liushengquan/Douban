package com.example.liushengquan.douban.view.impl.music

import android.graphics.Color
import android.os.Bundle
import com.example.liushengquan.douban.R
import com.example.liushengquan.douban.base.BaseActivity
import com.example.liushengquan.douban.bean.Constant
import com.example.liushengquan.douban.view.impl.book.BookDetailFragment
import kotlinx.android.synthetic.main.toolbar_base.*

/**
 * Created by liushengquan on 2018/3/14.
 */
class MusicDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        initView()
    }

    override fun initView() {
        var title = intent.getStringExtra(Constant.MUSIC_TITLE)
        //init toolbar
        setSupportActionBar(tb_base)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
//        supportActionBar!!.setHomeAsUpIndicator(R.drawable.back)
        tb_base.setNavigationOnClickListener({
            finish()
        })

        cltl_base.title = title
//        //通过CollapsingToolbarLayout修改字体颜色
        cltl_base.setExpandedTitleColor(Color.WHITE)    //设置还没收缩时状态下字体颜色
        cltl_base.setCollapsedTitleTextColor(Color.WHITE)   //设置收缩后Toolbar上字体的颜色

        var id = intent.getStringExtra(Constant.MUSIC_ID)
        var fragment = MusicDetailFragment.newInstance(id)
        addFragment(fragment, R.id.fl_base_content)
    }

    override fun initData() {
    }
}