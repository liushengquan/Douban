package com.example.liushengquan.douban.view.interf.music
import com.example.liushengquan.douban.base.IBaseView
import com.example.liushengquan.douban.bean.music.Music

/**
 * Created by liushengquan on 2017/12/31.
 */
interface IShowMusicDetail: IBaseView {
    fun showMusicDetail(music: Music)
    fun showMusicDetailFail(message: String)
}