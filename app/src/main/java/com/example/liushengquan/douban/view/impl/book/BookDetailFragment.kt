package com.example.liushengquan.douban.view.impl.book

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.example.liushengquan.douban.R
import com.example.liushengquan.douban.base.BaseFragment
import com.example.liushengquan.douban.bean.Constant
import com.example.liushengquan.douban.bean.book.Book
import com.example.liushengquan.douban.model.DatabaseRepertory
import com.example.liushengquan.douban.model.DoubanRepertory
import com.example.liushengquan.douban.presenter.BookPresenter
import com.example.liushengquan.douban.util.DisplayImgUtis
import com.example.liushengquan.douban.view.interf.book.IShowBookDetail

/**
 * Created by liushengquan on 2018/1/20.
 */
class BookDetailFragment : BaseFragment(),IShowBookDetail {

    lateinit var mRootView: View
    lateinit var iv_photo: ImageView
    lateinit var tv_title: TextView
    lateinit var tv_author: TextView
    lateinit var tv_publisher: TextView
    lateinit var tv_publish_time: TextView
    lateinit var tv_rating: TextView
    lateinit var tv_rating_person: TextView
    lateinit var tv_content_sunmery: TextView
    lateinit var tv_author_sunmary: TextView
    lateinit var tv_catalog: TextView
    lateinit var ratingbar: RatingBar

    lateinit var mRepertory: DoubanRepertory
    lateinit var mBookPresenter: BookPresenter

    lateinit var mId: String

    companion object {
        fun newInstance(id:String):BookDetailFragment{
            var fragment = BookDetailFragment()
            var bundle = Bundle()
            bundle.putString(Constant.BOOK_ID,id)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRepertory = DoubanRepertory(DatabaseRepertory())
        mBookPresenter = BookPresenter(mRepertory)
        mId = arguments.getString(Constant.BOOK_ID)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater!!.inflate(R.layout.fragment_book_detail,container,false)
        initView()
        initData()
        return mRootView
    }

    override fun initView() {
        iv_photo  = mRootView.findViewById(R.id.iv_book_detail_photo)
        tv_title =  mRootView.findViewById(R.id.tv_book_detail_title)
        tv_author =  mRootView.findViewById(R.id.tv_book_detail_author)
        tv_publisher=  mRootView.findViewById(R.id.tv_book_detail_publisher)
        tv_publish_time =  mRootView.findViewById(R.id.tv_book_detail_publish_time)
        tv_rating =  mRootView.findViewById(R.id.tv_book_detail_grade)
        tv_rating_person =  mRootView.findViewById(R.id.tv_book_detail_rating_person)
        tv_content_sunmery =  mRootView.findViewById(R.id.tv_book_detail_sunmery)
        tv_author_sunmary =  mRootView.findViewById(R.id.tv_book_detail_author_sunmury)
        tv_catalog =  mRootView.findViewById(R.id.tv_book_detail_catalog)
        ratingbar = mRootView.findViewById(R.id.rb_book_detail)
        ratingbar.stepSize = 0.5f
    }

    override fun initData() {
        mBookPresenter.getBookById(this,mId)
    }

    override fun isActive(): Boolean {
       return isAdded
    }

    override fun showBookDetail(book: Book) {
        if (book.images!=null&&!TextUtils.isEmpty(book.images!!.large))
            DisplayImgUtis.display(mContext, book.images!!.large, iv_photo)
        tv_title.text = book.title
        tv_author.text = "作者：" + book.author.toString()
        tv_publisher.text = "出版社：" + book.publisher
        tv_publish_time.text = "出版日期" + book.pubdate
        tv_rating.text = book.rating!!.average
        tv_rating_person.text = book.rating!!.numRaters.toString() + "人"
        tv_content_sunmery.text = book.summary
        tv_author_sunmary.text = book.author_intro
        tv_catalog.text = book.catalog

        //计算星星
        var num = book.rating!!.average!!.toFloat() /2
        ratingbar.rating = num
    }

    override fun showBookFail(message: String) {
        showToast(message)
    }
}