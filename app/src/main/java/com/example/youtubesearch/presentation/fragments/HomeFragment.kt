package com.example.youtubesearch.presentation.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubesearch.domain.models.VideoModel
import com.example.youtubesearch.presentation.MainViewModel
import com.example.youtubesearch.presentation.adapters.YoutubeSearchAdapter
import com.youtubesearch.R
import com.youtubesearch.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var mConstraintLayoutHome: ConstraintLayout

    private lateinit var youtubeSearchAdapter: YoutubeSearchAdapter
    private lateinit var recyclerViewHome: RecyclerView

    private lateinit var viewModel: MainViewModel

    private lateinit var searchView: SearchView
    var searchWord: String = ""
    private lateinit var mConnectivityManager: ConnectivityManager

    private var listVideos: List<VideoModel> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mConnectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        mConstraintLayoutHome = binding.constraintLayoutHome

        recyclerViewHome = binding.recyclerViewHome
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.videoModelList.observe(viewLifecycleOwner) {
            setAdapter(it)
            Log.d("fromApi", "observe: ${it.size}")
        }
    }

     fun getSearchResult(word: String) {
         Log.d("fromApi", "word in HomeFragment $word")
         viewModel.getSearchResult(word, requireContext())
    }

    private fun setAdapter(searchVideosList: List<VideoModel>) {
        youtubeSearchAdapter = YoutubeSearchAdapter()
        recyclerViewHome.adapter = youtubeSearchAdapter
        youtubeSearchAdapter.submitList(searchVideosList)
        youtubeSearchAdapter.onItemClickListener = {
            val bundle = bundleOf("videoId" to it.id.videoId)
            findNavController().navigate(R.id.nav_homeFragment_to_showVideoFragment, bundle)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.action_menu, menu)

        val mMenuItem: MenuItem = menu.findItem(R.id.action_search)

        searchView = mMenuItem.actionView as SearchView
        searchView.queryHint = getString(R.string.text_string_search_youtune_videos)
        searchView.imeOptions = EditorInfo.IME_ACTION_SEARCH
        searchVideos()
    }

    fun searchVideos() {
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchWord = query.toString()

                val imm: InputMethodManager? =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm?.hideSoftInputFromWindow(mConstraintLayoutHome.getWindowToken(), 0)

//                if (isNetworkConnected()) {
                    getSearchResult(searchWord)
//                } else {
//                    Toast.makeText(
//                        context,
//                        getString(R.string.text_string_please_check_network),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                searchWord = newText.toString()
//                if (isNetworkConnected()) {
//                    getSearchResult(searchWord)
//                } else {
//                    Toast.makeText(
//                        context,
//                        getString(R.string.text_string_please_check_network),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
                return true
            }

        })
    }

    fun isNetworkConnected(): Boolean {
        if (
            mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)?.state == NetworkInfo.State.CONNECTED ||
            mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)?.state == NetworkInfo.State.CONNECTED
        ) {
            return true
        } else {
            return false
        }
    }
}