package com.example.rightnewsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rightnewsapp.data.api.pojo.Article
import com.example.rightnewsapp.databinding.OneCategoryNewsBinding
import javax.inject.Inject

class OneCategoryAdapter @Inject constructor(): RecyclerView.Adapter<OneCategoryAdapter.OneHolder>() {
    lateinit var binding: OneCategoryNewsBinding
    var onCategoryClickins: ((Article)->Unit) ?= null

    inner class OneHolder(): RecyclerView.ViewHolder(binding.root)

    val diffUtil = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OneHolder {
        binding = OneCategoryNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OneHolder()

    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: OneHolder, position: Int) {
        binding.tvcategory.text = differ.currentList[position].title
        Glide.with(holder.itemView).load(differ.currentList[position].urlToImage).into(binding.imgcategory)
        onCategoryClickins!!.invoke(differ.currentList[position])
    }

}