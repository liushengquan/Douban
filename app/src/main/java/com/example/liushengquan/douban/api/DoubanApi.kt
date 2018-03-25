package com.example.liushengquan.douban.api

import com.example.liushengquan.douban.bean.book.Book
import com.example.liushengquan.douban.bean.book.Books
import com.example.liushengquan.douban.bean.event.Cities
import com.example.liushengquan.douban.bean.event.Event
import com.example.liushengquan.douban.bean.event.Events
import com.example.liushengquan.douban.bean.event.Location
import com.example.liushengquan.douban.bean.film.filmdetail.FilmDetail
import com.example.liushengquan.douban.bean.film.filmusbox.FilmUsBox
import com.example.liushengquan.douban.bean.film.top250.FilmTop
import com.example.liushengquan.douban.bean.filmlive.FilmLive
import com.example.liushengquan.douban.bean.music.Music
import com.example.liushengquan.douban.bean.music.Musics
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * Created by liushengquan on 2017/12/25.
 */
interface DoubanApi {

    /**
     * 热映中
     * @return
     */

    @GET("v2/movie/in_theaters")
    fun getLiveFilm(): Observable<FilmLive>

    /**
     * 北美榜单
     * @return
     */

    @GET("v2/movie/us_box")
    fun getUsBox(): Observable<FilmUsBox>

    /**
     * 获取top250
     * @param start
     * @param count
     * @return
     */

    @GET("v2/movie/top250")
    fun getTop250(@Query("start") start: Int, @Query("count") count: Int): Observable<FilmTop>

    /**
     * 获取电影详情
     * @param id
     * @return
     */

    @GET("v2/movie/subject/{id}")
    fun getFilmDetail(@Path("id") id: String): Observable<FilmDetail>


    /**
     * 根据tag获取图书
     * @param tag
     * @return
     */

    @GET("v2/book/search")
    fun searchBookByTag(@Query("tag") tag: String): Observable<Books>

    @GET("v2/book/{id}")
    fun getBookDetail(@Path("id") id: String): Observable<Book>

    /**
     * 根据tag获取music
     * @param tag
     * @return
     */

    @GET("v2/music/search")
    fun searchMusicByTag(@Query("tag") tag: String): Observable<Musics>

    @GET("v2/music/{id}")
    fun getMusicDetail(@Path("id") id: String): Observable<Music>

    /**
     * 获取城市列表
     * @return
     */
    @GET("v2/loc/list")
    fun getCities(): Observable<Cities>

    @GET("v2/loc/{id}")
    fun getCity(@Path("id") id: String): Observable<Location>

    /**
     * 获取同城活动
     * @return
     */
    @GET("v2/event/list")
    fun getEvents(@Query("loc") location: String, @Query("day_type") day_type: String ,@Query("type") type: String): Observable<Events>

    @GET("v2/event/{id}")
    fun getEvent(@Path("id") id: String): Observable<Event>
}