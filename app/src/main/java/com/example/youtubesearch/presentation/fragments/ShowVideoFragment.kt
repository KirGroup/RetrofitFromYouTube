package com.example.youtubesearch.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
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
        startWebView()
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun startWebView(){
        binding.webView.webViewClient = WebViewClient()
        binding.webView.apply {
            settings.javaScriptEnabled = true
            arguments?.getString("url")?.let { loadUrl(it) }
        }

    }

}