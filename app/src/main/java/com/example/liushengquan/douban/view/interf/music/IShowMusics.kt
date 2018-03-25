package com.example.liushengquan.douban.view.interf.music

import com.example.liushengquan.douban.base.IBaseView
import com.example.liushengquan.douban.bean.music.Musics

/**
 * Created by liushengquan on 2018/1/6.
 */
interface IShowMusics: IBaseView {
    fun showMusics(musics: Musics, isLoadMore: Boolean)
    fun showMusicsFail(message: String)
    fun showOrHideIndecator(visible: Boolean)
}