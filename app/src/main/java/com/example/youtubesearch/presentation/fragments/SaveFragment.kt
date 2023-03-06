package com.example.youtubesearch.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubesearch.domain.models.VideoModel
import com.example.youtubesearch.presentation.adapters.YoutubeSearchAdapter
import com.example.youtubesearch.presentation.adapters.favoriteadapter.YoutubeFavoriteAdapter
import com.youtubesearch.R
import com.youtubesearch.databinding.FragmentHomeBinding
import com.youtubesearch.databinding.FragmentSaveBinding

class SaveFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var youtubeSearchAdapter: YoutubeFavoriteAdapter
    private lateinit var recyclerViewFavorite: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewFavorite = binding.recyclerViewHome
//        setFavoriteAdapter()
    }

    private fun setFavoriteAdapter(favoriteVideosList: List<VideoModel>){
        youtubeSearchAdapter = YoutubeFavoriteAdapter()
        recyclerViewFavorite.adapter = youtubeSearchAdapter
        youtubeSearchAdapter.submitList(favoriteVideosList)
        youtubeSearchAdapter.onItemClickListener = {
            val bundle = bundleOf("videoId" to it.id.videoId)
            findNavController().navigate(R.id.nav_homeFragment_to_showVideoFragment, bundle)
        }
    }
}