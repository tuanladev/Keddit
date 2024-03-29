package com.tuanlat.keddit.features.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tuanlat.keddit.R
import com.tuanlat.keddit.commons.InfiniteScrollListener
import com.tuanlat.keddit.commons.RedditNews
import com.tuanlat.keddit.commons.RedditNewsItem
import com.tuanlat.keddit.commons.RxBaseFragment
import com.tuanlat.keddit.commons.extensions.inflate
import com.tuanlat.keddit.features.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.news_fragment.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Fragment hiển thị tin tức
 */
//class NewsFragment : Fragment(){
class NewsFragment : RxBaseFragment(){
    // Danh sach tin
//    private var newsList: RecyclerView? = null
    private var redditNews: RedditNews? = null

    private val newsManager by lazy {
        NewsManager()
    }
    /**
     * HIển thị
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*
        val view = container?.inflate(R.layout.news_fragment)

        newsList = view?.findViewById(R.id.rcList) as RecyclerView?
        newsList?.setHasFixedSize(true) // use this setting to improve performance
        newsList?.layoutManager = LinearLayoutManager(context)
        return view
        */
        //gọi từ extensions ra
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rcList.setHasFixedSize(true)
       // rcList.layoutManager = LinearLayoutManager(context)
        val linearLayout = LinearLayoutManager(context)
        rcList.layoutManager = linearLayout
        rcList.clearOnScrollListeners()
        rcList.addOnScrollListener(InfiniteScrollListener({ requestNews() }, linearLayout))

        //khoi tao adapter
        initAdapter()


        /** tao thu 10 doi tuong de test
        if (savedInstanceState == null) {
            val news = mutableListOf<RedditNewsItem>()
            for (i in 1..10) {
                news.add(
                    RedditNewsItem(
                    "author$i",
                    "Title $i",
                    i, // number of comments
                    1457207701L - i * 200, // time
                    "http://lorempixel.com/200/200/technics/$i", // image url
                    "url"
                )
                )
            }

            //them doi tuong vao danh sach
            (rcList.adapter as NewsAdapter).addNews(news)
        */
        //request doi tuong
            requestNews()

        }

    /**
     * request du lieu tu server
     */
    private fun requestNews() {
    //    val subscription = newsManager.getNews()
        /**
         * first time will send empty string for after parameter.
         * Next time we will have redditNews set with the next page to
         * navigate with the after param.
         */
        val subscription = newsManager.getNews(redditNews?.after ?: "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { retrievedNews ->
                   // (rcList.adapter as NewsAdapter).addNews(retrievedNews as MutableList<RedditNewsItem>)
                    redditNews = retrievedNews as RedditNews
                    (rcList.adapter as NewsAdapter).addNews(retrievedNews.news as MutableList<RedditNewsItem>)
                },
                { e ->
                    Snackbar.make(rcList, e.message ?: "", Snackbar.LENGTH_LONG).show()
                }
            )
        subscriptions.add(subscription)
    }


    private fun initAdapter() {
        if (rcList.adapter == null) {
            rcList.adapter = NewsAdapter()
        }
    }

}