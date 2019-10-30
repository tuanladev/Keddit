package com.tuanlat.keddit.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Json: https://www.reddit.com/top.json?limit=10
 *
 * interface gọi vào top.json
 * lấy giá trị về model RedditNewsResponse
 */
interface  RedditApi {
    @GET("/top.json")
    fun getTop(@Query("after") after: String,
               @Query("limit") limit: String): Call<RedditNewsResponse>;
}