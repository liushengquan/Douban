package com.example.liushengquan.douban.bean.book

/**
 * Created by liushengquan on 2017/12/24.
 */
class Book {
    var id: String? = null
    var isbn10: String? = null
    var isbn13: String? = null
    var title: String? = null
    var origin_title: String? = null
    var alt_title: String? = null
    var subtitle: String? = null
    var url: String? = null
    var alt: String? = null
    var image: String? = null
    var images: Image? = null
    var author: List<String>? = null
    var translator: List<String>? = null
    var publisher: String? = null
    var pubdate: String? = null
    var rating: Rating? = null
    var tags: List<Tag>? = null
    var binding: String? = null
    var price: String? = null
    var series: Series? = null
    var pages: String? = null
    var author_intro: String? = null
    var summary: String? = null
    var catalog: String? = null
    var ebook_url: String? = null
    var ebook_price: String? = null
}