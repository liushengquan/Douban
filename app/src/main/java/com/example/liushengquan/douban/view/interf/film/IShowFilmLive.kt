package com.example.liushengquan.douban.view.interf.film

import com.example.liushengquan.douban.base.IBaseView
import com.example.liushengquan.douban.bean.filmlive.FilmLive

/**
 * Created by liushengquan on 2017/12/31.
 */
interface IShowFilmLive: IBaseView {

    fun showFilmLive(filmLive: FilmLive)
    fun showFilmLiveFail(message: String)
    fun showOrHideIndecator(visible: Boolean)
}