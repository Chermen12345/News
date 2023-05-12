package com.example.rightnewsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rightnewsapp.data.api.pojo.Article
import com.example.rightnewsapp.databinding.OneDailyItemBinding
import com.example.rightnewsapp.databinding.OneSearchItemBinding
import javax.inject.Inject

class SearchAdapter @Inject constructor(): RecyclerView.Adapter<SearchAdapter.SearchHolder>() {
    lateinit var binding: OneSearchItemBinding
    var onitemclick: ((Article)->Unit) ?= null
    inner class SearchHolder(): RecyclerView.ViewHolder(binding.root)

    val callback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,callback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        binding = OneSearchItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchHolder()
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        Glide.with(holder.itemView).load(differ.currentList[position].urlToImage).into(
            binding.imageView
        )
        binding.textView3.text = differ.currentList[position].title
        onitemclick!!.invoke(differ.currentList[position])
    }


}