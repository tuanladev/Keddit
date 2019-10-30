package com.tuanlat.keddit.commons

import com.tuanlat.keddit.commons.adapter.AdapterConstants
import com.tuanlat.keddit.commons.adapter.ViewType


//đối tượng RedditNews có ID = 1
data class RedditNewsItem(
    val author: String,
    val title: String,
    val numComments: Int,
    val created: Long,
    val thumbnail: String,
    val url: String
) : ViewType {
    override fun getViewType(): Int = AdapterConstants.NEWS

}