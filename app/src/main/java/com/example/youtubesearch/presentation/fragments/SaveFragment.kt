package com.example.youtubesearch.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubesearch.domain.models.VideoModel
import com.example.youtubesearch.presentation.FavoriteViewModel
import com.example.youtubesearch.presentation.adapters.favoriteadapter.YoutubeFavoriteAdapter
import com.youtubesearch.R
import com.youtubesearch.databinding.FragmentSaveBinding

class SaveFragment : Fragment() {

    private lateinit var binding: FragmentSaveBinding
    private lateinit var youtubeSearchAdapter: YoutubeFavoriteAdapter
    private lateinit var recyclerViewFavorite: RecyclerView
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewFavorite = binding.recyclerViewFavorite

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return FavoriteViewModel(requireActivity().application) as T
            }
        }).get(FavoriteViewModel::class.java)
        viewModel.getFavoriteVideoList()
        viewModel.favoriteVideoModelList.observe(viewLifecycleOwner) {
            setFavoriteAdapter(it)
        }
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