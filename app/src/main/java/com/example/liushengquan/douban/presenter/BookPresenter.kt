package com.example.liushengquan.douban.presenter

import com.example.liushengquan.douban.base.IBasePresenter
import com.example.liushengquan.douban.bean.book.Book
import com.example.liushengquan.douban.bean.book.Books
import com.example.liushengquan.douban.model.DoubanRepertory
import com.example.liushengquan.douban.view.interf.book.IShowBookDetail
import com.example.liushengquan.douban.view.interf.book.IShowBooks
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by liushengquan on 2018/1/6.
 */
class BookPresenter(var repertory: DoubanRepertory): IBasePresenter {

    fun searchBookByTag(iShowBooks: IShowBooks, TAG: String, isLoadMore: Boolean) {
        repertory.searchBookByTag(TAG)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: Subscriber<Books>() {
                    override fun onError(e: Throwable?) {
                        iShowBooks.showBooksFail(e.toString())
                    }
                    override fun onNext(t: Books?) {
                        if(t!=null)
                            iShowBooks.showBooks(t,isLoadMore)
                        else
                            iShowBooks.showBooksFail("搜索图书列表失败")
                    }
                    override fun onCompleted() {
                    }
                })

    }

    fun getBookById(iShowBookDetail: IShowBookDetail, id: String){
        repertory.getBookDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Book>(){
                    override fun onCompleted() {
                    }

                    override fun onNext(t: Book?) {
                        if(t!=null)
                            iShowBookDetail.showBookDetail(t)
                        else
                            iShowBookDetail.showBookFail("图书信息不存在")
                    }

                    override fun onError(e: Throwable?) {
                        iShowBookDetail.showBookFail(e.toString())
                    }

                })
    }

    override fun start() {
    }
}