package com.example.liushengquan.douban.bean.film.filmdetail

import com.example.liushengquan.douban.bean.film.Image
import com.example.liushengquan.douban.bean.film.Rating

/**
 * Created by liushengquan on 2017/12/24.
 */
class FilmDetail {
    var id: String? = null
    var title: String? = null
    var original_title: String? = null
    var aka: List<String>? = null
    var alt: String? = null
    var mobile_url: String? = null
    var rating: Rating? = null
    var ratings_count: Int = 0
    var wish_count: Int = 0
    var collect_count: Int = 0
    var do_count: String? = null
    var images: Image? = null
    var subtype: String? = null
    var directors: List<Celebrity>? = null
    var casts: List<Celebrity>? = null
    var writers: List<Celebrity>? = null
    var website: String? = null
    var douban_site: String? = null
    var pubdates: List<String>? = null
    var mainland_pubdate: String? = null
    var year: String? = null
    var languages: List<String>? = null
    var durations: List<String>? = null
    var genres: List<String>? = null
    var countries: List<String>? = null
    var summary: String? = null
    var comments_count: Int = 0
    var reviews_count: Int = 0
    var seasons_count: String? = null
    var current_season: String? = null
    var episodes_count: String? = null
    var schedule_url: String? = null
    var trailer_urls: List<String>? = null
    var clip_urls: List<String>? = null
    var blooper_urls: List<String>? = null
    var photos: List<String>? = null
    var popular_reviews: List<String>? = null
}