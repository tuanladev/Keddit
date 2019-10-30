package com.tuanlat.keddit.features.news.adapter

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.tuanlat.keddit.commons.RedditNewsItem
import com.tuanlat.keddit.commons.adapter.AdapterConstants
import com.tuanlat.keddit.commons.adapter.ViewType
import com.tuanlat.keddit.commons.adapter.ViewTypeDelegateAdapter

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items: ArrayList<ViewType> //khoi tao danh sach cac item kieu (ViewType = 1 | 2)

    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

    //item loading gia tri id = 2
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    //khoi tao gia tri
    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())  //tao doi tuong 2 = loading
        delegateAdapters.put(AdapterConstants.NEWS, NewsDelegateAdapter())  //tao doi tuong 1 = news
        items = ArrayList()
        items.add(loadingItem)  //danh sach chi co gia tri 1 hoac 2

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType)!!.onCreateViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position))!!.onBindViewHolder(holder, this.items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }

    /**
     * them tin moi vào danh sách adapter
     * Ở đây ta lưu ý, danh sách đã có mục Loading --> do ta thêm ở đầu
     * Cần phải xóa bỏ loadding, sau đó thêm danh sách
     * cuối cùng thêm loading vào cuối danh sách
     */
    fun addNews(news: MutableList<RedditNewsItem>) {

        //xoa bo ten o cuoi danh sach --> tuc la cai loading
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        //Them toan bo tin moi va chen item loading vao cuoi danh sach
        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1 /* plus loading item */)
    }

    /**
     * xóa bỏ toàn bộ đối tượng và thêm mới
     */
    fun clearAndAddNews(news: List<RedditNewsItem>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    /**
     * lấy danh sách news. ở đâu danh sách gồm có
     * filter ==> trả về kết quả 1 là news
     * map ==> đối chiếu để lấy ra chi tiết news
     */
    fun getNews(): List<RedditNewsItem> {
        return items
            .filter { it.getViewType() == AdapterConstants.NEWS }
            .map { it as RedditNewsItem }
    }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex

}