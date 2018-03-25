package com.example.liushengquan.douban.bean.music

import com.example.liushengquan.douban.bean.book.Rating
import java.util.ArrayList

/**
 * Created by liushengquan on 2017/12/25.
 */
class Music {
    var rating: Rating? = null
    var author: ArrayList<Author>? = null
    var alt_title = ""
    var image = ""
    var tags: ArrayList<Tags>? = null
    var mobile_link = ""
    var attrs: Attrs? = null
    var title = ""
    var summary = ""
    var alt = ""
    var id: String? = null
}

