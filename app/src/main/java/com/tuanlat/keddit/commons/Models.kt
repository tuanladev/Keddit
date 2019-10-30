package com.tuanlat.keddit.commons

import com.tuanlat.keddit.commons.adapter.AdapterConstants
import com.tuanlat.keddit.commons.adapter.ViewType

//đối tượng News gồm danh sách đầu và cuối
data class RedditNews(
    val after: String,
    val before: String,
    val news: List<RedditNewsItem>)

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