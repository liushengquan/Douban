package com.example.liushengquan.douban.model

import com.example.liushengquan.douban.DoubanApp
import com.example.liushengquan.douban.api.ApiFactory
import com.example.liushengquan.douban.api.DoubanApi
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
import rx.Observable

/**
 * Created by liushengquan on 2017/12/28.
 */
class DoubanRepertory(var databaseRepertory: DatabaseRepertory): IDataSource {

    var doubanApi:DoubanApi?

    init {
         doubanApi = ApiFactory.getDoubanApiService(DoubanApp.getAppContext())
    }

    override fun getLiveFilm(): Observable<FilmLive> {
        return doubanApi!!.getLiveFilm()
    }

    override fun getUsBox(): Observable<FilmUsBox> {
        return doubanApi!!.getUsBox()
    }

    override fun getTop250(start: Int, count: Int): Observable<FilmTop> {
        return doubanApi!!.getTop250(start,count)
    }

    override fun getFilmDetail(id: String): Observable<FilmDetail> {
        return doubanApi!!.getFilmDetail(id)
    }

    override fun searchBookByTag(tag: String): Observable<Books> {
        return doubanApi!!.searchBookByTag(tag)
    }

    override fun getBookDetail(id: String): Observable<Book> {
        return doubanApi!!.getBookDetail(id)
    }

    override fun searchMusicByTag(tag: String): Observable<Musics> {
        return doubanApi!!.searchMusicByTag(tag)
    }

    override fun getMusicDetail(id: String): Observable<Music> {
        return doubanApi!!.getMusicDetail(id)
    }

    override fun getCities(): Observable<Cities> {
        return doubanApi!!.getCities()
    }

    override fun getCity(id: String): Observable<Location> {
        return doubanApi!!.getCity(id)
    }

    override fun getEvents(location: String, day_type: String , type: String): Observable<Events> {
        return doubanApi!!.getEvents(location,day_type,type)
    }

    override fun getEvent(id: String): Observable<Event> {
        return doubanApi!!.getEvent(id)
    }
}