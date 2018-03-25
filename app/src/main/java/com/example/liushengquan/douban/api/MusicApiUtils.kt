package com.example.liushengquan.douban.api

/**
 * Created by liushengquan on 2017/12/25.
 */
class MusicApiUtils {
    companion object {
        var Music_Titles = arrayOf("流行", "经典", "韩系", "欧美")
        var PopulayTag = arrayOf("歌声", "青春", "回忆", "孙燕姿", "周杰伦", "林俊杰", "陈奕迅", "王力宏", "邓紫棋", "风声", "海边", "童话", "美女", "一生", "爱", "爱情", "远方", "缘分", "天空", "张国荣", "黄家驹", "　beyond", "黑豹乐队")
        var ClassicTag = arrayOf("张国荣", "黄家驹", "　beyond", "黑豹乐队", "王菲", "五月天", "陈奕迅", "古巨基", "杨千嬅", "叶倩文", "许嵩", "刘德华", "邓丽君", "张学友")
        var KereaTag = arrayOf("bigbang", "rain", "PSY", "李弘基", "李承哲", "金钟国", "李孝利", "孝琳", "IU", "EXO", "T-ara", "东方神起", "Epik High", "Girl's Day", " 紫雨林", "Zebra")
        var AmericanTag = arrayOf("Jay-Z", "Justin Bieber", "James Blunt", "Eminem", "Akon", "Adele", "Avril Lavigne", "Beyoncé", "Lady GaGa", "Taylor Swift", "Alicia Keys", "Owl City", "Coldplay")

        fun getMusicTag(pos: Int): Array<String>? {
            when (pos) {
                0 -> return PopulayTag
                1 -> return ClassicTag
                2 -> return KereaTag
                3 -> return AmericanTag
            }
            return null
        }
    }
}