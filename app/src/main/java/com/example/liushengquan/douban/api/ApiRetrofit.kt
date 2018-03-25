package com.example.liushengquan.douban.api

import android.content.Context
import com.example.liushengquan.douban.DoubanApp
import com.example.liushengquan.douban.util.NetUtils
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by liushengquan on 2018/1/6.
 */
class ApiRetrofit private constructor(context: Context){

    val DOUBAN_BASE_URL = "https://api.douban.com/"

    var mDoubanApiService: DoubanApi
    var mContext = context

    var interceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain?): Response {
            val cacheBuilder = CacheControl.Builder()
            cacheBuilder.maxAge(0, TimeUnit.SECONDS)
            cacheBuilder.maxStale(365, TimeUnit.DAYS)
            val cacheControl = cacheBuilder.build()

            var request = chain!!.request()
            if (!NetUtils.checkNetWorkIsAvailable(mContext)) {
                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build()

            }
            val originalResponse = chain.proceed(request)
            if (NetUtils.checkNetWorkIsAvailable(mContext)) {
                val maxAge = 0 // read from cache
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build()
            } else {
                val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build()
            }
        }
    }

    init {
        val httpCacheDirectory = File(DoubanApp.getAppContext().cacheDir, "responses")
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize.toLong())

        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(DOUBAN_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        mDoubanApiService = retrofit.create(DoubanApi::class.java)
    }

    companion object {
        fun getDoubanApiService(context: Context):DoubanApi? {
           return ApiRetrofit(context).mDoubanApiService
        }
    }

}