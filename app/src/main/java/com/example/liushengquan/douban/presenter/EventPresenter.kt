package com.example.liushengquan.douban.presenter

import com.example.liushengquan.douban.base.IBasePresenter
import com.example.liushengquan.douban.bean.event.Cities
import com.example.liushengquan.douban.bean.event.Event
import com.example.liushengquan.douban.bean.event.Events
import com.example.liushengquan.douban.model.DoubanRepertory
import com.example.liushengquan.douban.view.interf.event.IShowCities
import com.example.liushengquan.douban.view.interf.event.IShowEventDetail
import com.example.liushengquan.douban.view.interf.event.IShowEvents
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by liushengquan on 2018/1/6.
 */
class EventPresenter(var repertory: DoubanRepertory) : IBasePresenter {

    fun getCities(iShowCities: IShowCities){
        repertory.getCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Cities>() {
                    override fun onError(e: Throwable?) {
                        iShowCities.showCitiesFail(e.toString())
                    }

                    override fun onNext(t: Cities?) {
                        if (t != null)
                            iShowCities.showCities(t)
                        else
                            iShowCities.showCitiesFail("获取城市列表失败")
                    }

                    override fun onCompleted() {
                    }
                })

    }

    fun getEvents(iShowEvents: IShowEvents,location: String,day_type: String , type: String){
        repertory.getEvents(location,day_type,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Events>() {
                    override fun onError(e: Throwable?) {
                        iShowEvents.showEventsFail(e.toString())
                    }

                    override fun onNext(t: Events?) {
                        if (t != null)
                            iShowEvents.showEvents(t)
                        else
                            iShowEvents.showEventsFail("获取活动列表失败")
                    }
                    override fun onCompleted() {
                    }
                })

    }

    fun getEventById(iShowEventDetail: IShowEventDetail,id: String){
        repertory.getEvent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Event>() {
                    override fun onError(e: Throwable?) {
                        iShowEventDetail.showEventFail(e.toString())
                    }

                    override fun onNext(t: Event?) {
                        if (t != null)
                            iShowEventDetail.showEventDetail(t)
                        else
                            iShowEventDetail.showEventFail("获取活动信息失败")
                    }
                    override fun onCompleted() {
                    }
                })
    }

    override fun start() {
    }

}