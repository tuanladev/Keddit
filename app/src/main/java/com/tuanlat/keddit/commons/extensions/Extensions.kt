@file:JvmName("ExtensionsUtils")

//chuc nang mo rong
//ten cua tep
//tao class utils
package com.tuanlat.keddit.commons.extensions

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.tuanlat.keddit.R

/**
 * Hàm để rút gọn việc gọi layout
 * việc gọi layout sẽ là:
 * container?.inflate(R.layout.news_fragment)
 */
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}



//Hàm load anh từ URL
fun ImageView.loadImg(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        Picasso.with(context).load(R.mipmap.ic_launcher).into(this)
    } else {
        Picasso.with(context).load(imageUrl).into(this)
    }
}