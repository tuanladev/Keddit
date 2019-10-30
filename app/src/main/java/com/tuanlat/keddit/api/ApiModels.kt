package com.tuanlat.keddit.api

/**
 * Json: https://www.reddit.com/top.json?limit=10
 *
 * object		{2}
 *      kind	:	Listing
 *      data		{5}
 *          modhash	:
 *          dist	:	10
 *          children		[10]
 *              0		{2}
 *              1		{2}
 *              2		{2}
 *                  kind	:	t3
 *                  data		{103}
 *      after	:	t3_dowyxt
 *      before	:	null
 */

class RedditNewsResponse(val data: RedditDataResponse)
class RedditDataResponse(
    val children: List<RedditChildrenResponse>,
    val after: String?,
    val before: String?
)

class RedditChildrenResponse(val data: RedditNewsDataResponse)

class RedditNewsDataResponse(
    val author: String,
    val title: String,
    val num_comments: Int,
    val created: Long,
    val thumbnail: String,
    val url: String
)