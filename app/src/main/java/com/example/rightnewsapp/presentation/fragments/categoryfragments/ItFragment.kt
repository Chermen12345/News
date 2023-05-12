package com.example.rightnewsapp.presentation.fragments.categoryfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rightnewsapp.R
import com.example.rightnewsapp.data.api.instance.Consts
import com.example.rightnewsapp.data.api.pojo.Article
import com.example.rightnewsapp.databinding.FragmentItBinding
import com.example.rightnewsapp.domain.viewmodels.DbViewModel
import com.example.rightnewsapp.domain.viewmodels.HomeViewModel
import com.example.rightnewsapp.presentation.adapters.OneCategoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ItFragment : Fragment() {
    lateinit var binding: FragmentItBinding
    @Inject
    lateinit var badapter: OneCategoryAdapter
    private val dbviem: DbViewModel by viewModels()
    private val viewm: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewm.oneCategoryNews("Information Technology", Consts.API_KEY)
        setUpRcBusiness()
        observeLd()

        //db impl
        badapter.onCategoryClickins = {
            insertFromcat(it)
            deleteFromCat(it)
        }
    }

    fun setUpRcBusiness(){
        binding.rcit.apply {
            adapter = badapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    fun observeLd(){
        viewm.observeCategoryLd().observe(viewLifecycleOwner, {
            badapter.differ.submitList(it)
        })
    }
    //db
    private fun insertFromcat(article: Article){
        badapter.binding.btsavecategory.setOnClickListener {
            dbviem.insertNew(article)
        }
    }
    private fun deleteFromCat(article: Article){
        badapter.binding.btdeletecategory.setOnClickListener {
            dbviem.deleteNew(article)
        }
    }




}