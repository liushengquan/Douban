package com.example.liushengquan.douban.view.interf.film

import com.example.liushengquan.douban.base.IBaseView
import com.example.liushengquan.douban.bean.film.filmdetail.FilmDetail

/**
 * Created by liushengquan on 2017/12/31.
 */
interface IShowFilmDetail: IBaseView {
    fun showFilmDetail(filmDetail: FilmDetail)
    fun showFilmDetailFail(message: String)
}