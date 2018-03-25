package com.example.liushengquan.douban.view.interf.event

import com.example.liushengquan.douban.base.IBaseView
import com.example.liushengquan.douban.bean.event.Cities

/**
 * Created by liushengquan on 2017/12/31.
 */
interface IShowCities: IBaseView {
    fun showCities(cities: Cities)
    fun showCitiesFail(message: String)
    fun showOrHideIndecator(visible: Boolean)
}