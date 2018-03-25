package com.example.liushengquan.douban.view.interf.event

import com.example.liushengquan.douban.base.IBaseView
import com.example.liushengquan.douban.bean.event.Events

/**
 * Created by liushengquan on 2017/12/31.
 */
interface IShowEvents: IBaseView {
    fun showEvents(events: Events)
    fun showEventsFail(message: String)
    fun showOrHideIndecator(visible: Boolean)
}