package com.example.liushengquan.douban.view.impl.book

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.liushengquan.douban.R
import com.example.liushengquan.douban.adapter.BookAdapter
import com.example.liushengquan.douban.base.BaseFragment
import com.example.liushengquan.douban.bean.Constant
import com.example.liushengquan.douban.bean.book.Books
import com.example.liushengquan.douban.model.DatabaseRepertory
import com.example.liushengquan.douban.model.DoubanRepertory
import com.example.liushengquan.douban.presenter.BookPresenter
import com.example.liushengquan.douban.util.AnimationUtils
import com.example.liushengquan.douban.view.interf.OnItemClickListener
import com.example.liushengquan.douban.view.interf.book.IShowBooks
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by liushengquan on 2018/1/21.
 */
class BookReadFragment : BaseFragment() , IShowBooks, OnItemClickListener<BookReadFragment.BookData> {

    lateinit var rv_book: RecyclerView
    lateinit var refresh_book: SwipeRefreshLayout
    lateinit var mRootView: View

    lateinit var mRepertory: DoubanRepertory
    lateinit var mBookPresenter: BookPresenter
    lateinit var mBookAdapter: BookAdapter
    lateinit var mLayoutManager: GridLayoutManager

    lateinit var mTag: String
    val mBooks = mutableListOf<BookData>()
    var mExecutor: ExecutorService  = Executors.newCachedThreadPool()

    companion object {
        fun newInstance(tag: String):BookReadFragment{
            var fragment = BookReadFragment()
            var bundle = Bundle()
            bundle.putString(Constant.BOOK_TAG,tag)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRepertory = DoubanRepertory(DatabaseRepertory())
        mBookPresenter = BookPresenter(mRepertory)
        mTag = arguments.getString(Constant.BOOK_TAG)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            AnimationUtils.setupWindowExitAnimations(activity.window)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater!!.inflate(R.layout.fragment_book_read,container,false)
        initView()
        initData()
        return mRootView
    }

    override fun initView() {
        rv_book = mRootView.findViewById(R.id.rv_book_read)
        rv_book.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    var lastVisibleItem = mLayoutManager.findLastVisibleItemPosition()
                    if (mLayoutManager.itemCount == 1) {
                            mBookAdapter.updateState(mBookAdapter.LOAD_NONE)
                        return
                    }
                    if (lastVisibleItem + 1 == mLayoutManager.itemCount) {
                        if (mBookAdapter != null) {
                            mBookPresenter.searchBookByTag(this@BookReadFragment,mTag,true)
                            mBookAdapter.updateState(mBookAdapter.LOAD_PULL_TO)
                            mBookAdapter.updateState(mBookAdapter.LOAD_MORE)
                        }
                    }
                }
            }
        })
        refresh_book = mRootView.findViewById(R.id.refresh_book_read)
        refresh_book.setOnRefreshListener {
            mExecutor.execute {
                Thread.sleep(1000)
                activity.runOnUiThread {
                  Toast.makeText(mContext,"已刷新",Toast.LENGTH_LONG).show()
                    refresh_book.isRefreshing = false
                }
            }
        }
        showOrHideIndecator(true)
    }

    override fun initData() {
        mBookAdapter = BookAdapter()
        mBookAdapter.setOnItemClickListener(this)
        mLayoutManager = GridLayoutManager(context,4)
        rv_book.layoutManager = mLayoutManager
        rv_book.adapter = mBookAdapter

        if(!TextUtils.isEmpty(mTag))
            mBookPresenter!!.searchBookByTag(this,mTag,false)
    }

    override fun isActive(): Boolean {
        return isAdded
    }

    override fun showBooks(books: Books?, isLoadMore: Boolean) {
        if (!isLoadMore)
            mBooks.clear()
        for (book in books!!.books!!) {
            var bookData = BookData(book!!.id, book!!.title, book!!.images!!.large, book!!.rating!!.average)
            mBooks.add(bookData)
        }
        mBookAdapter.setDatas(mBooks)
        mBookAdapter.updateState(mBookAdapter.LOAD_END)

        showOrHideIndecator(false)
    }

    override fun showBooksFail(message: String) {
        showToast(message)
        showOrHideIndecator(false)
    }

    override fun showOrHideIndecator(visible: Boolean) {
        refresh_book!!.isRefreshing = visible
    }

    override fun OnItemClick(view: View, position: Int, data: BookData) {
        var intent = Intent()
        intent.setClass(activity,BookDetailActivity::class.java)
        intent.putExtra(Constant.BOOK_ID,data.id)
        intent.putExtra(Constant.BOOK_TITLE,data.title)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val options = ActivityOptions.makeSceneTransitionAnimation(activity, view.findViewById<ImageView>(R.id.iv_book_photo), "book_image")
            startActivity(intent, options.toBundle())
        } else {
            startActivityByIntent(intent)
        }
    }

    data class BookData(var id:String?,var title:String?,var img: String?, var grade: String?)
}