package com.example.rightnewsapp.domain.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rightnewsapp.data.api.pojo.Article
import com.example.rightnewsapp.domain.repos.DbRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DbViewModel @Inject constructor(val repo: DbRepo): ViewModel() {
    fun insertNew(article: Article) = viewModelScope.launch(Dispatchers.IO){
        repo.insertNew(article)
    }
    fun deleteNew(article: Article) = viewModelScope.launch(Dispatchers.IO){
        repo.deleteNew(article)
    }
    fun getAllNew() = repo.getAllNews()
}