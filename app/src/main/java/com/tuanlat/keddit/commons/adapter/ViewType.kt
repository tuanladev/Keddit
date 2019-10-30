package com.tuanlat.keddit.commons.adapter

//tra ve kieu hien thi của item trong recycleview

/**
 * trả về id của kiểu
 * 1 = @News
 * 2 = Loading
 */
interface ViewType {
    fun getViewType(): Int
}