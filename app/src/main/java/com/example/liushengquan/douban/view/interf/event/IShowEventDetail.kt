package com.example.liushengquan.douban.view.interf.event

import com.example.liushengquan.douban.base.IBaseView
import com.example.liushengquan.douban.bean.event.Event

/**
 * Created by liushengquan on 2017/12/31.
 */
interface IShowEventDetail: IBaseView {
    fun showEventDetail(event: Event)
    fun showEventFail(message: String)
}