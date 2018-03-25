package com.example.liushengquan.douban.view.interf.book

import com.example.liushengquan.douban.base.IBaseView
import com.example.liushengquan.douban.bean.book.Books

/**
 * Created by liushengquan on 2017/12/31.
 */
interface IShowBooks: IBaseView {
    fun showBooks(books: Books?, isLoadMore: Boolean)
    fun showBooksFail(message: String)
    fun showOrHideIndecator(visible: Boolean)
}