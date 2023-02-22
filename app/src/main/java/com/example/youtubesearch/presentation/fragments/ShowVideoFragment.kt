package com.example.youtubesearch.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.youtubesearch.domain.models.VideoModel
import com.youtubesearch.databinding.FragmentShowVideoBinding

class ShowVideoFragment : Fragment() {

    private lateinit var binding: FragmentShowVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

//    companion object {
//        fun newInstance(videoModel: VideoModel): ShowVideoFragment {
//            val args = Bundle().apply {
//                putParcelable("ITEM_DATA", videoModel)
//            }
//            val fragment = DetailsFragment()
//            fragment.arguments = args
//            return fragment
//        }
//    }
}