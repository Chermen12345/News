package com.example.rightnewsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rightnewsapp.data.api.pojo.Article
import com.example.rightnewsapp.databinding.OneDailyItemBinding
import com.example.rightnewsapp.domain.viewmodels.DbViewModel
import com.example.rightnewsapp.presentation.fragments.HomeFragment
import javax.inject.Inject


class HomeAdapter @Inject constructor() : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    lateinit var binding: OneDailyItemBinding
    var onItemAdded: ((Article) -> Unit) ?= null
    inner class HomeHolder():RecyclerView.ViewHolder(binding.root)

    val callback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(
                oldItem: Article,
                newItem: Article
        ): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(
                oldItem: Article,
                newItem: Article
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        binding = OneDailyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeHolder()
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        binding.tvtitle.text = differ.currentList[position].title

        Glide.with(holder.itemView).load(differ.currentList[position].urlToImage).into(
            binding.img
        )
        onItemAdded!!.invoke(differ.currentList[position])




    }


}