package com.example.liushengquan.douban.api

/**
 * Created by liushengquan on 2017/12/25.
 */
class EventApiUtils {
    companion object {
        val EventType = arrayOf("all","music","drama","exhibition","salon","party","sports","travel","commonweal","film")
        val DayType = arrayOf("future","week","weekend","today","tomorrow")

        fun getEventType(pos: Int) = when(pos){
            in 0..9 -> EventType[pos]
            else -> EventType[0]
        }
    }
}