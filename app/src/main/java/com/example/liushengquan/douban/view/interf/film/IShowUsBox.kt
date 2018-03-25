package com.example.liushengquan.douban.view.interf.film

import com.example.liushengquan.douban.base.IBaseView
import com.example.liushengquan.douban.bean.film.filmusbox.FilmUsBox

/**
 * Created by liushengquan on 2017/12/31.
 */
interface IShowUsBox: IBaseView {
    fun showFilmUsBox(filmUsBox: FilmUsBox)
    fun showFilmUsBoxFail(message: String)
    fun showOrHideIndecator(visible: Boolean)
}