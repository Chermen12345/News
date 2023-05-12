package com.example.rightnewsapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rightnewsapp.R
import com.example.rightnewsapp.data.api.instance.Consts.API_KEY
import com.example.rightnewsapp.data.api.pojo.Article
import com.example.rightnewsapp.databinding.FragmentSearchBinding
import com.example.rightnewsapp.domain.viewmodels.DbViewModel
import com.example.rightnewsapp.domain.viewmodels.HomeViewModel
import com.example.rightnewsapp.presentation.adapters.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    @Inject
    lateinit var adapter: SearchAdapter
    private val dbviem: DbViewModel by viewModels()
    private val searchViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcSearch()
        onClickSearch()
        //db impl
        adapter.onitemclick = {
            insertFromcat(it)
            deleteFromCat(it)
        }
    }
    private fun rcSearch(){
        binding.rcsearch.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.rcsearch.adapter = adapter
    }
    private fun onClickSearch(){
        binding.btsearch.setOnClickListener {
            observeSearchLd()
            searchViewModel.searchNews(binding.edsearch.text.toString(), API_KEY)
            binding.edsearch.text.clear()
        }
    }
    private fun observeSearchLd(){
        searchViewModel.observeSearchLd().observe(viewLifecycleOwner, {
            adapter.differ.submitList(it)
        })
    }
    //db
    private fun insertFromcat(article: Article){
        adapter.binding.btsearchsave.setOnClickListener {
            dbviem.insertNew(article)
        }
    }
    private fun deleteFromCat(article: Article){
        adapter.binding.btdeletesearch.setOnClickListener {
            dbviem.deleteNew(article)
        }
    }


}