package com.tuanlat.keddit.features.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuanlat.keddit.R
import com.tuanlat.keddit.commons.RedditNewsItem
import com.tuanlat.keddit.commons.extensions.inflate
import com.tuanlat.keddit.features.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.news_fragment.*

/**
 * Fragment hiển thị tin tức
 */
class NewsFragment : Fragment(){
    // Danh sach tin
//    private var newsList: RecyclerView? = null


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
        rcList.layoutManager = LinearLayoutManager(context)

        //khoi tao adapter
        initAdapter()


        //tao thu 10 doi tuong de test
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
        }
    }

    private fun initAdapter() {
        if (rcList.adapter == null) {
            rcList.adapter = NewsAdapter()
        }
    }

}