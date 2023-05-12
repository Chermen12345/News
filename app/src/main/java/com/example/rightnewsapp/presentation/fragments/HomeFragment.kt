package com.example.rightnewsapp.presentation.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rightnewsapp.R
import com.example.rightnewsapp.data.api.instance.Consts.API_KEY
import com.example.rightnewsapp.data.api.pojo.Article
import com.example.rightnewsapp.databinding.FragmentHomeBinding
import com.example.rightnewsapp.domain.viewmodels.DbViewModel
import com.example.rightnewsapp.domain.viewmodels.HomeViewModel
import com.example.rightnewsapp.presentation.activities.MainActivity
import com.example.rightnewsapp.presentation.adapters.HomeAdapter
import com.example.rightnewsapp.presentation.adapters.ViewPagerHomeAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    val tabtexts = arrayListOf<String>("Business", "IT", "Movie", "Sport")
    private lateinit var binding: FragmentHomeBinding
    @Inject
    lateinit var adapter: HomeAdapter

    private val dbViewModel by viewModels<DbViewModel>()
    lateinit var viewPagerHomeAdapter: ViewPagerHomeAdapter
    private val homeviewmodel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewPagerHomeAdapter = ViewPagerHomeAdapter(this)
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeviewmodel.getAllDailyNews("us", API_KEY)
        observeLdDaily()
        setUpRcDaily()

        //viewpager
        setUpViewPager()

        //db
        adapter.onItemAdded = {art->
            insertNew(art)
            deleteNew(art)
        }


    }
    //daily news
    fun setUpRcDaily(){
        binding.rcdaily.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        binding.rcdaily.adapter = adapter

    }
    fun observeLdDaily(){
        homeviewmodel.getld().observe(viewLifecycleOwner, Observer{
            adapter.differ.submitList(it)
        })
    }

    //set up viewpager
    fun setUpViewPager(){
        binding.apply {
            viewPager.adapter = viewPagerHomeAdapter
            TabLayoutMediator(tab, viewPager){
                tab, position -> tab.text = tabtexts[position]
            }.attach()
        }
    }
    //insert To Db
    private fun insertNew(article: Article) {
        adapter.binding.btsave.setOnClickListener {
            dbViewModel.insertNew(article)
        }

    }
    private fun deleteNew(article: Article){
        adapter.binding.btdeletenew.setOnClickListener {
            dbViewModel.deleteNew(article)
        }
    }












}