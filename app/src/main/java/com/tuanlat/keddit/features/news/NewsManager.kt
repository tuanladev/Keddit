package com.tuanlat.keddit.features.news

import com.tuanlat.keddit.api.RestAPI
import com.tuanlat.keddit.commons.RedditNewsItem
import rx.Observable

/**
 * Class thực hiện giao thức rxJava de lấy dữ liệu từ server
 *
 */
//class NewsManager {
class NewsManager(private val api: RestAPI = RestAPI()){

    /**
     * Dữ liệu mẫu

    fun getNews(): Observable<List<RedditNewsItem>> {
        return Observable.create {
                subscriber ->

            val news = mutableListOf<RedditNewsItem>()
            for (i in 1..10) {
                news.add(RedditNewsItem(
                    "author$i",
                    "Title $i",
                    i, // number of comments
                    1457207701L - i * 200, // time
                    "http://lorempixel.com/200/200/technics/$i", // image url
                    "url"
                ))
            }
            subscriber.onNext(news)
        }
    }
     */
    fun getNews(limit: String = "10"): Observable<List<RedditNewsItem>> {
        return Observable.create {
                subscriber ->
            val callResponse = api.getNews("", limit)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val news = response.body().data.children.map {
                    val item = it.data
                    RedditNewsItem(item.author, item.title, item.num_comments,
                        item.created, item.thumbnail, item.url)
                }
                subscriber.onNext(news)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}
