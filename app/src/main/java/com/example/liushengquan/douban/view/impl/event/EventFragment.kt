package com.example.liushengquan.douban.view.impl.event

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import com.example.liushengquan.douban.R
import com.example.liushengquan.douban.adapter.EventAdapter
import com.example.liushengquan.douban.api.EventApiUtils
import com.example.liushengquan.douban.base.BaseFragment
import com.example.liushengquan.douban.bean.Constant
import com.example.liushengquan.douban.bean.event.Events
import com.example.liushengquan.douban.model.DatabaseRepertory
import com.example.liushengquan.douban.model.DoubanRepertory
import com.example.liushengquan.douban.presenter.EventPresenter
import com.example.liushengquan.douban.view.interf.OnItemClickListener
import com.example.liushengquan.douban.view.interf.event.IShowEvents

/**
 * Created by liushengquan on 2018/1/13.
 */
class EventFragment : BaseFragment(), IShowEvents, OnItemClickListener<EventFragment.EventData> {

    lateinit var rv_event: RecyclerView
    lateinit var spinner_event: Spinner
    lateinit var mRootView: View

    lateinit var mRepertory: DoubanRepertory
    lateinit var mEventPresenter: EventPresenter
    lateinit var mAdapter: EventAdapter
    lateinit var mLayoutManager: GridLayoutManager

    val mEvents = mutableListOf<EventData>()
    val cities_loc = arrayOf("118281","118282","108288","108296")


    companion object {
        fun newInstance(): EventFragment {
            var fragment = EventFragment()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRepertory = DoubanRepertory(DatabaseRepertory())
        mEventPresenter = EventPresenter(mRepertory)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater!!.inflate(R.layout.fragment_event,container,false)
        initView()
        initData()
        return mRootView
    }


    override fun initView() {
        rv_event = mRootView.findViewById(R.id.rv_event)
        rv_event.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    var lastVisibleItem = mLayoutManager.findLastVisibleItemPosition()
                    if (mLayoutManager.itemCount == 1) {
                        if (mAdapter != null) {
                            mAdapter.updateState(mAdapter.LOAD_NONE)
                        }
                        return
                    }
                    if (lastVisibleItem + 1 == mLayoutManager.itemCount) {
                        if (mAdapter != null) {
                            var loc = spinner_event.selectedItemPosition
                            mEventPresenter.getEvents(this@EventFragment,cities_loc[loc], EventApiUtils.DayType[1],EventApiUtils.EventType[0])
                            mAdapter.updateState(mAdapter.LOAD_PULL_TO)
                            mAdapter.updateState(mAdapter.LOAD_MORE)
                        }
                    }
                }
            }
        })
        spinner_event = mRootView.findViewById(R.id.spinner_event)
        spinner_event.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mEventPresenter.getEvents(this@EventFragment,cities_loc[position], EventApiUtils.DayType[1],EventApiUtils.EventType[0])
            }
        }
    }

    override fun isActive(): Boolean {
        return isAdded
    }

    override fun initData() {
        mAdapter = EventAdapter()
        mAdapter.setOnItemClickListener(this)
        mLayoutManager = GridLayoutManager(mContext,3)
        rv_event.layoutManager = mLayoutManager
        rv_event.adapter = mAdapter

        var loc = spinner_event.selectedItemPosition
        mEventPresenter.getEvents(this,cities_loc[loc], EventApiUtils.DayType[1],EventApiUtils.EventType[0])
    }

    override fun showEvents(events: Events) {
        mEvents.clear()
        for(event in events!!.events){
            var eventData = EventData(event.id,event.title,event.image,event.begin_time)
            mEvents.add(eventData)
        }
        mAdapter.setDatas(mEvents)
        mAdapter.updateState(mAdapter.LOAD_END)
    }

    override fun showEventsFail(message: String) {
        showToast(message)
    }

    override fun showOrHideIndecator(visible: Boolean) {
    }

    override fun OnItemClick(view: View, position: Int, data: EventData) {
        var intent = Intent()
        intent.setClass(activity,EventDetailActivity::class.java)
        intent!!.putExtra(Constant.EVENT_ID,data!!.id)
        intent!!.putExtra(Constant.EVENT_TITLE,data!!.title)
        startActivityByIntent(intent)
    }

    data class EventData(var id:String?,var title:String?,var img: String?, var date: String?)
}