package com.example.liushengquan.douban.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.widget.PopupWindow
import android.widget.Toast
import com.example.liushengquan.douban.DoubanApp
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.liushengquan.douban.R


/**
 * Created by liushengquan on 2017/12/31.
 */
abstract class BaseFragment : Fragment() ,BaseInit{

    lateinit var mContext: Context
    var mPopLoading: PopupWindow? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = DoubanApp.getAppContext()
        //初始化loading
        val view = LayoutInflater.from(context).inflate(R.layout.popup_loading, null, false)
        mPopLoading = PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true)
    }

    /**
     * 根据传入的类(class)打开指定的activity
     * @param pClass
     */
    protected fun startActivity(pClass: Class<*>) {
        val intent = Intent()
        intent.setClass(activity, pClass)
        startActivity(intent)
    }

    protected fun startActivityByIntent(pIntent: Intent) {
        startActivity(pIntent)
    }

    protected fun showToast(resId:Int){
        showToast(getString(resId))
    }

    protected fun showToast(message:String){
        Toast.makeText(mContext,message,Toast.LENGTH_LONG).show()
    }

    protected fun showProgress(resId:Int){
        var title = mContext.resources.getString(resId)
        showProgress(title)
    }

    protected fun showProgress(title: String){
        if(mPopLoading!=null&&!mPopLoading!!.isShowing)
            mPopLoading!!.showAtLocation(view,Gravity.CENTER,0,0)
    }

    protected fun hideProgress(){
        if(mPopLoading!=null&&mPopLoading!!.isShowing)
            mPopLoading!!.dismiss()
    }
}