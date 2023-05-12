package com.example.rightnewsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.AsyncListUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rightnewsapp.data.api.pojo.Article
import com.example.rightnewsapp.databinding.OneSavedItemBinding
import javax.inject.Inject

class SavedAdapter @Inject constructor(): RecyclerView.Adapter<SavedAdapter.SavedHolder>() {
    lateinit var binding: OneSavedItemBinding
    var onItem: ((Article)->Unit) ?= null
    inner class SavedHolder(): RecyclerView.ViewHolder(binding.root)

    val util = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, util)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedHolder {
        binding = OneSavedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedHolder()
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SavedHolder, position: Int) {
        binding.savetitle.text = differ.currentList[position].title
        binding.tvdate.text = "published at: " + differ.currentList[position].publishedAt
        Glide.with(holder.itemView).load(differ.currentList[position].urlToImage).into(binding
            .savedimg)
        onItem!!.invoke(differ.currentList[position])

    }

}