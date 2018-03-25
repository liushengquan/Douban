package com.example.liushengquan.douban.model

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
class DatabaseRepertory: IDataSource {
    override fun getEvents(location: String, day_type: String, type: String): Observable<Events> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun getLiveFilm(): Observable<FilmLive> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUsBox(): Observable<FilmUsBox> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTop250(start: Int, count: Int): Observable<FilmTop> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFilmDetail(id: String): Observable<FilmDetail> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchBookByTag(tag: String): Observable<Books> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBookDetail(id: String): Observable<Book> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchMusicByTag(tag: String): Observable<Musics> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMusicDetail(id: String): Observable<Music> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCities(): Observable<Cities> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCity(id: String): Observable<Location> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getEvent(id: String): Observable<Event> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}