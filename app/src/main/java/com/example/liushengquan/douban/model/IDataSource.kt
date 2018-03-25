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
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * Created by liushengquan on 2017/12/24.
 */
interface IDataSource {

    fun getLiveFilm(): Observable<FilmLive>

    fun getUsBox(): Observable<FilmUsBox>

    fun getTop250(start: Int, count: Int): Observable<FilmTop>

    fun getFilmDetail(id: String): Observable<FilmDetail>

    fun searchBookByTag(tag: String): Observable<Books>

    fun getBookDetail(id: String): Observable<Book>

    fun searchMusicByTag(tag: String): Observable<Musics>

    fun getMusicDetail(id: String): Observable<Music>

    fun getCities(): Observable<Cities>

    fun getCity(id: String): Observable<Location>

    fun getEvents(location: String, day_type: String , type: String): Observable<Events>

    fun getEvent(id: String): Observable<Event>
}