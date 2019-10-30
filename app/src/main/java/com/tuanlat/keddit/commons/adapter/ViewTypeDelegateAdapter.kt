package com.tuanlat.keddit.commons.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


/***
 * Ghi đè lên 2 hàm create và bind dữ liệu của adapter
 */
interface ViewTypeDelegateAdapter {

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}