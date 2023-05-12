package com.example.rightnewsapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rightnewsapp.R
import com.example.rightnewsapp.data.api.pojo.Article
import com.example.rightnewsapp.databinding.FragmentSavedBinding
import com.example.rightnewsapp.domain.viewmodels.DbViewModel
import com.example.rightnewsapp.presentation.adapters.SavedAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SavedFragment : Fragment() {
    private lateinit var binding: FragmentSavedBinding
    private val dbviewm: DbViewModel by viewModels()
    @Inject
    lateinit var adapter: SavedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //db impl
        setUpRc()
        getAllItems()
        adapter.onItem = {
            deleteFromSaved(it)
        }
    }
    //db
    private fun setUpRc(){
        binding.rcsaved.adapter = adapter
        binding.rcsaved.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
    private fun getAllItems(){
        dbviewm.getAllNew().observe(viewLifecycleOwner,{artlist->
            adapter.differ.submitList(artlist)
        })
    }
    private fun deleteFromSaved(article: Article){
        adapter.binding.btdeletefromsaved.setOnClickListener {
            dbviewm.deleteNew(article)
        }
    }


}