package com.example.rightnewsapp.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.rightnewsapp.R
import com.example.rightnewsapp.data.api.instance.Consts
import com.example.rightnewsapp.databinding.ActivityMainBinding
import com.example.rightnewsapp.domain.viewmodels.HomeViewModel
import com.example.rightnewsapp.presentation.adapters.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //functions
        setBtNav()
    }
    private fun setBtNav(){
        val navigation = Navigation.findNavController(this, R.id.navhost)
        binding.btnav.setupWithNavController(navigation)
    }

}