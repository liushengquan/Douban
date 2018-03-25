package com.example.liushengquan.douban.view.interf.film

import com.example.liushengquan.douban.base.IBaseView
import com.example.liushengquan.douban.bean.film.top250.FilmTop

/**
 * Created by liushengquan on 2017/12/31.
 */
interface IShowTop250: IBaseView {
    fun showTop250(filmTop: FilmTop, isLoadMore: Boolean)
    fun showTop250Fail(message: String)
    fun showOrHideIndecator(visible: Boolean)
}