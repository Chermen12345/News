package com.example.rightnewsapp.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.rightnewsapp.presentation.fragments.categoryfragments.BusinessFragment
import com.example.rightnewsapp.presentation.fragments.categoryfragments.ItFragment
import com.example.rightnewsapp.presentation.fragments.categoryfragments.MovieFragment
import com.example.rightnewsapp.presentation.fragments.categoryfragments.SportFragment

class ViewPagerHomeAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return BusinessFragment()
            1 -> return ItFragment()
            2 -> return MovieFragment()

        }
        return SportFragment()
    }


}