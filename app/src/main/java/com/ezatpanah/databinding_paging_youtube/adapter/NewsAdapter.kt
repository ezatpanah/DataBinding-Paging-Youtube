package com.ezatpanah.databinding_paging_youtube.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ezatpanah.databinding_paging_youtube.databinding.ItemNewsBinding
import com.ezatpanah.databinding_paging_youtube.response.ResponseTopHeadline
import javax.inject.Inject

class NewsAdapter @Inject constructor() :  PagingDataAdapter<ResponseTopHeadline.Article,NewsAdapter.MyViewHolder>(differCallback) {

    lateinit var binding: ItemNewsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemNewsBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class MyViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ResponseTopHeadline.Article) {
            binding.article = article
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }
    private var onItemClickListener: ((ResponseTopHeadline.Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (ResponseTopHeadline.Article) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<ResponseTopHeadline.Article>() {
            override fun areItemsTheSame(oldItem: ResponseTopHeadline.Article, newItem: ResponseTopHeadline.Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: ResponseTopHeadline.Article, newItem: ResponseTopHeadline.Article): Boolean {
                return oldItem == newItem
            }
        }
    }

}