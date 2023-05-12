package com.example.rightnewsapp.domain.viewmodels

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rightnewsapp.R
import com.example.rightnewsapp.data.api.pojo.Article
import com.example.rightnewsapp.domain.repos.ApiRepo
import com.example.rightnewsapp.presentation.fragments.SearchFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repo: ApiRepo): ViewModel(){
    //part of getting daily news

    val dailyld = MutableLiveData<List<Article>>()
    val lddaily : LiveData<List<Article>>
        get() = dailyld

    fun getAllDailyNews(country: String,apikey: String) = viewModelScope.launch(Dispatchers.IO){
        repo.getAllDailyNews(country, apikey).let {
            if (it.isSuccessful){
                dailyld.postValue(it.body()!!.articles)
            }else{
                Log.d("taggy", it.message())
            }
        }
    }

    fun getld(): LiveData<List<Article>>{
        return lddaily
    }
    //search news
    val searchNewsLd = MutableLiveData<List<Article>>()
    val ldsearch: LiveData<List<Article>>
    get() = searchNewsLd
    fun searchNews(q: String, apikey: String) = viewModelScope.launch(Dispatchers.IO){
        repo.searchAllNews(q, apikey).let {
            if (it.isSuccessful){
                searchNewsLd.postValue(it.body()!!.articles)

            }else{
                Log.d("taggy", it.message())
            }
        }
    }
    fun observeSearchLd() = ldsearch
    //one category
    val onecategory = MutableLiveData<List<Article>>()
    val categoryone: LiveData<List<Article>>
    get() = onecategory
    fun oneCategoryNews(q: String, apikey: String) = viewModelScope.launch(Dispatchers.IO){
        repo.getOneCategoryNews(q, apikey).let {
            if (it.isSuccessful){
                onecategory.postValue(it.body()!!.articles)
            }else{
                Log.d("taggy", it.message())
            }
        }
    }
    fun observeCategoryLd() = categoryone



}