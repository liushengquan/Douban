package com.example.liushengquan.douban.presenter

import com.example.liushengquan.douban.base.IBasePresenter
import com.example.liushengquan.douban.bean.film.filmdetail.FilmDetail
import com.example.liushengquan.douban.bean.film.filmusbox.FilmUsBox
import com.example.liushengquan.douban.bean.film.top250.FilmTop
import com.example.liushengquan.douban.bean.filmlive.FilmLive
import com.example.liushengquan.douban.model.DoubanRepertory
import com.example.liushengquan.douban.view.interf.film.IShowFilmDetail
import com.example.liushengquan.douban.view.interf.film.IShowFilmLive
import com.example.liushengquan.douban.view.interf.film.IShowTop250
import com.example.liushengquan.douban.view.interf.film.IShowUsBox
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by liushengquan on 2018/1/6.
 */
class FilmPresenter(var repertory: DoubanRepertory):IBasePresenter {

    fun getFilmLive(iShowFilmLive: IShowFilmLive) {
        repertory.getLiveFilm()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<FilmLive>() {
                    override fun onError(e: Throwable?) {
                        iShowFilmLive.showFilmLiveFail(e.toString())
                    }

                    override fun onNext(t: FilmLive?) {
                        if (t != null)
                            iShowFilmLive.showFilmLive(t)
                        else
                            iShowFilmLive.showFilmLiveFail("获取热映电影失败")
                    }

                    override fun onCompleted() {
                    }
                })
    }

    fun getFilmTop250(iShowTop250: IShowTop250,start: Int,count: Int,isLoadMore: Boolean) {
        repertory.getTop250(start,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<FilmTop>() {
                    override fun onError(e: Throwable?) {
                        iShowTop250.showTop250Fail(e.toString())
                    }

                    override fun onNext(t: FilmTop?) {
                        if (t != null)
                            iShowTop250.showTop250(t,isLoadMore)
                        else
                            iShowTop250.showTop250Fail("获取Top250电影失败")
                    }

                    override fun onCompleted() {
                    }
                })
    }

    fun getFilmUsBox(iShowUsBox: IShowUsBox) {
        repertory.getUsBox()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<FilmUsBox>() {
                    override fun onError(e: Throwable?) {
                        iShowUsBox.showFilmUsBoxFail(e.toString())
                    }

                    override fun onNext(t: FilmUsBox?) {
                        if (t != null)
                            iShowUsBox.showFilmUsBox(t)
                        else
                            iShowUsBox.showFilmUsBoxFail("获取北美电影失败")
                    }

                    override fun onCompleted() {
                    }
                })
    }

    fun getFilmDetail(iShowFilmDetail: IShowFilmDetail,id: String) {
        repertory.getFilmDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<FilmDetail>() {
                    override fun onError(e: Throwable?) {
                        iShowFilmDetail.showFilmDetailFail(e.toString())
                    }

                    override fun onNext(t: FilmDetail?) {
                        if (t != null)
                            iShowFilmDetail.showFilmDetail(t)
                        else
                            iShowFilmDetail.showFilmDetailFail("获取电影详细信息失败")
                    }

                    override fun onCompleted() {
                    }
                })
    }

    override fun start() {
    }
}