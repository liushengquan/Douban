package com.example.liushengquan.douban.view.impl.event

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import com.example.liushengquan.douban.R
import com.example.liushengquan.douban.base.BaseFragment
import com.example.liushengquan.douban.bean.Constant
import com.example.liushengquan.douban.bean.event.Event
import com.example.liushengquan.douban.model.DatabaseRepertory
import com.example.liushengquan.douban.model.DoubanRepertory
import com.example.liushengquan.douban.presenter.EventPresenter
import com.example.liushengquan.douban.util.DisplayImgUtis
import com.example.liushengquan.douban.view.interf.event.IShowEventDetail

/**
 * Created by liushengquan on 2018/3/15.
 */
class EventDetailFragment : BaseFragment(), IShowEventDetail {

    lateinit var mRootView: View
    lateinit var iv_photo: ImageView
    lateinit var tv_title: TextView
    lateinit var tv_joiner: TextView
    lateinit var tv_like: TextView
    lateinit var tv_join: TextView
    lateinit var wv_detail: WebView

    lateinit var mRepertory: DoubanRepertory
    lateinit var mEventPresenter: EventPresenter

    lateinit var mId: String

    companion object {
        fun newInstance(id:String): EventDetailFragment{
            var fragment = EventDetailFragment()
            var bundle = Bundle()
            bundle.putString(Constant.EVENT_ID,id)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRepertory = DoubanRepertory(DatabaseRepertory())
        mEventPresenter = EventPresenter(mRepertory)
        mId = arguments.getString(Constant.EVENT_ID)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater!!.inflate(R.layout.fragment_event_detail,container,false)
        initView()
        initData()
        return mRootView
    }

    override fun initView() {
        iv_photo  = mRootView.findViewById(R.id.iv_event_detail_photo)
        tv_title =  mRootView.findViewById(R.id.tv_event_detail_title)
        tv_joiner =  mRootView.findViewById(R.id.tv_event_detail_joiner)
        tv_join=  mRootView.findViewById(R.id.tv_event_join)
        tv_like=  mRootView.findViewById(R.id.tv_event_like)
        wv_detail =  mRootView.findViewById(R.id.wv_event__detail)
    }

    override fun isActive(): Boolean {
        return isAdded
    }

    override fun initData() {
        mEventPresenter.getEventById(this,mId)
    }

    override fun showEventDetail(event: Event) {
        if (!TextUtils.isEmpty(event.image))
            DisplayImgUtis.display(mContext, event.image!!, iv_photo)
        tv_title.text = event.title
        tv_joiner.text = event.wisher_count.toString()+ "人感兴趣/" + event.participant_count+"人要参加"
        wv_detail.loadDataWithBaseURL(null,event.content, "text/html", "UTF-8",null);
    }

    override fun showEventFail(message: String) {
        showToast(message)
    }
}