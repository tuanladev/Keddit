package com.tuanlat.keddit.features.news.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tuanlat.keddit.R
import com.tuanlat.keddit.commons.RedditNewsItem
import com.tuanlat.keddit.commons.adapter.ViewType
import com.tuanlat.keddit.commons.adapter.ViewTypeDelegateAdapter
import com.tuanlat.keddit.commons.extensions.getFriendlyTime
import com.tuanlat.keddit.commons.extensions.inflate
import com.tuanlat.keddit.commons.extensions.loadImg
import kotlinx.android.synthetic.main.news_item.view.*

class NewsDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TurnsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
        holder.bind(item as RedditNewsItem)
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.news_item)) {

        fun bind(item: RedditNewsItem) = with(itemView) {
            //Picasso.with(itemView.context).load(item.thumbnail).into(img_thumbnail)
            imgThumbnail.loadImg(item.thumbnail)
            txtDescription.text = item.title
            txtAuthor.text = item.author
            comments.text = "${item.numComments} comments"
            time.text = item.created.getFriendlyTime()
        }
    }
}
