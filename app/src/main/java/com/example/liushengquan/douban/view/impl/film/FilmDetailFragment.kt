package com.example.liushengquan.douban.view.impl.film

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.liushengquan.douban.R
import com.example.liushengquan.douban.adapter.FilmPersonAdapter
import com.example.liushengquan.douban.base.BaseFragment
import com.example.liushengquan.douban.bean.Constant
import com.example.liushengquan.douban.bean.film.filmdetail.FilmDetail
import com.example.liushengquan.douban.model.DatabaseRepertory
import com.example.liushengquan.douban.model.DoubanRepertory
import com.example.liushengquan.douban.presenter.FilmPresenter
import com.example.liushengquan.douban.util.DisplayImgUtis
import com.example.liushengquan.douban.view.interf.film.IShowFilmDetail

/**
 * Created by liushengquan on 2018/1/20.
 */
class FilmDetailFragment: BaseFragment(), IShowFilmDetail {

    lateinit var iv_photo:ImageView
    lateinit var tv_rating: TextView
    lateinit var tv_num: TextView
    lateinit var tv_date: TextView
    lateinit var tv_type: TextView
    lateinit var tv_contury: TextView
    lateinit var tv_title: TextView
    lateinit var tv_sunmery: TextView
    lateinit var recyclerview: RecyclerView
    lateinit var tv_seemore: TextView

    var mRepertory: DoubanRepertory? = null
    var mFilmPresenter : FilmPresenter? = null
    var mRootView: View? = null
    var mPersonAdatper: FilmPersonAdapter? = null
    var mId = ""
    val mPersonList = mutableListOf<FilmPerson>()

    companion object {
        fun newInstance(id: String): FilmDetailFragment {
            var fragment = FilmDetailFragment()
            var bundle = Bundle()
            bundle.putString(Constant.FILM_ID,id)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRepertory = DoubanRepertory(DatabaseRepertory())
        mFilmPresenter = FilmPresenter(mRepertory as DoubanRepertory)
        mId = arguments.getString(Constant.FILM_ID)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater!!.inflate(R.layout.fragment_film_detail, container, false)
        initView()
        initData()
        return mRootView
    }

    override fun initView() {
        iv_photo = mRootView!!.findViewById(R.id.iv_film_detail_photo)
        tv_rating = mRootView!!.findViewById(R.id.tv_film_detail_rating)
        tv_num = mRootView!!.findViewById(R.id.tv_film_detail_num)
        tv_date = mRootView!!.findViewById(R.id.tv_film_detail_date)
        tv_type = mRootView!!.findViewById(R.id.tv_film_detail_type)
        tv_contury = mRootView!!.findViewById(R.id.tv_film_detail_contury)
        tv_title = mRootView!!.findViewById(R.id.tv_film_detail_title)
        tv_sunmery = mRootView!!.findViewById(R.id.tv_film_detail_sunmery)
        recyclerview = mRootView!!.findViewById(R.id.recyclerview_film_detail)
        tv_seemore = mRootView!!.findViewById(R.id.tv_film_detail_see_more)
    }

    override fun isActive(): Boolean {
        return isAdded
    }

    override fun initData() {
        mPersonAdatper = FilmPersonAdapter()
        recyclerview.layoutManager = LinearLayoutManager(mContext)
        recyclerview.adapter = mPersonAdatper

        if(!TextUtils.isEmpty(mId))
            mFilmPresenter!!.getFilmDetail(this,mId)

    }

    override fun showFilmDetail(filmDetail: FilmDetail) {
        if (filmDetail.images != null && filmDetail.images!!.large != null)
            DisplayImgUtis.display(mContext, filmDetail.images!!.large!!, iv_photo)
        if (filmDetail.rating != null) {
            tv_rating.text = "评分" + filmDetail.rating!!.average
        }
        tv_num.text = filmDetail.ratings_count.toString() + "人评分"
        tv_date.text = filmDetail.year + "年  出品"
        if (filmDetail!!.countries != null && filmDetail!!.countries!!.isNotEmpty()) {
            tv_contury!!.text = filmDetail!!.countries!![0]
        }
        if (filmDetail!!.genres != null && filmDetail!!.genres!!.isNotEmpty()) {
            val stringBuilder = StringBuilder()
            for (s in filmDetail!!.genres!!) {
                stringBuilder.append(s + "/")
            }
            tv_type.text = stringBuilder.toString().substring(0, stringBuilder.toString().length - 1)
        }
        tv_sunmery.text = filmDetail!!.summary
        tv_title.text = filmDetail!!.original_title + " [原名]"

        initFilmPerson(filmDetail)
        mPersonAdatper!!.addDatas(mPersonList)
        mPersonAdatper!!.notifyDataSetChanged()
    }

    fun initFilmPerson(filmDetail: FilmDetail){
        if (filmDetail.directors != null && filmDetail.directors!!.isNotEmpty()){
            for(director in filmDetail.directors!!){
                var filmPerson = FilmPerson(director!!.name,director!!.avatars!!.medium,"[导演]")
                mPersonList.add(filmPerson)
            }
        }
        if (filmDetail!!.casts != null && filmDetail!!.casts!!.isNotEmpty()){
            for(cast in filmDetail!!.casts!!){
                var filmPerson = FilmPerson(cast!!.name,cast!!.avatars!!.medium,"[演员]")
                mPersonList.add(filmPerson)
            }
        }
    }

    override fun showFilmDetailFail(message: String) {
        showToast(message)
    }

    data class FilmPerson(var name:String?,var img: String?, var type: String?)
}