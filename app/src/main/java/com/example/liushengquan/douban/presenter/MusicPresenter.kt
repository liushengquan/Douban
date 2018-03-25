package com.example.liushengquan.douban.presenter

import com.example.liushengquan.douban.base.IBasePresenter
import com.example.liushengquan.douban.bean.music.Music
import com.example.liushengquan.douban.bean.music.Musics
import com.example.liushengquan.douban.model.DoubanRepertory
import com.example.liushengquan.douban.view.interf.music.IShowMusicDetail
import com.example.liushengquan.douban.view.interf.music.IShowMusics
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by liushengquan on 2018/1/6.
 */
class MusicPresenter(var repertory: DoubanRepertory) : IBasePresenter {

    fun searchMusicByTag(iShowMusics: IShowMusics, TAG: String, isLoadMore: Boolean) {
        repertory.searchMusicByTag(TAG)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Musics>() {
                    override fun onError(e: Throwable?) {
                        iShowMusics.showMusicsFail(e.toString())
                    }

                    override fun onNext(t: Musics?) {
                        if (t != null)
                            iShowMusics.showMusics(t, isLoadMore)
                        else
                            iShowMusics.showMusicsFail("搜索音乐列表失败")
                    }

                    override fun onCompleted() {
                    }
                })
    }

    fun getMusicById(iShowMusicDetail: IShowMusicDetail, id: String) {
        repertory.getMusicDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Music>() {
                    override fun onCompleted() {
                    }

                    override fun onNext(t: Music?) {
                        if (t != null)
                            iShowMusicDetail.showMusicDetail(t)
                        else
                            iShowMusicDetail.showMusicDetailFail("音乐信息不存在")
                    }

                    override fun onError(e: Throwable?) {
                        iShowMusicDetail.showMusicDetailFail(e.toString())
                    }

                })
    }

    override fun start() {
    }
}