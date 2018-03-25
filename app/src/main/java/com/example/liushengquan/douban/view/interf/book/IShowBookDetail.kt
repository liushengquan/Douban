package com.example.liushengquan.douban.view.interf.book

import com.example.liushengquan.douban.base.IBaseView
import com.example.liushengquan.douban.bean.book.Book

/**
 * Created by liushengquan on 2017/12/31.
 */
interface IShowBookDetail: IBaseView{
    fun showBookDetail(book: Book)
    fun showBookFail(message: String)
}