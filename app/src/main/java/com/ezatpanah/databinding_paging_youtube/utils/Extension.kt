package com.ezatpanah.databinding_paging_youtube.utils

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.initRecycler(layoutManager: RecyclerView.LayoutManager, adapter: RecyclerView.Adapter<*>) {
    this.adapter=adapter
    this.layoutManager=layoutManager
}
