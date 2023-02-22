package com.example.youtubesearch.presentation.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubesearch.presentation.adapters.YoutubeSearchAdapter
import com.example.youtubesearch.data.database.VideosDataBase
import com.example.youtubesearch.domain.models.VideoModel
import com.example.youtubesearch.presentation.MainViewModel
import com.youtubesearch.R
import com.youtubesearch.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var mConstraintLayoutHome: ConstraintLayout

    private lateinit var youtubeSearchAdapter: YoutubeSearchAdapter
    private lateinit var recyclerViewHome: RecyclerView

    private lateinit var viewModel: MainViewModel

    var mSearchWord: String = ""
    private lateinit var mConnectivityManager: ConnectivityManager

    private lateinit var dbVideos: VideosDataBase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        mConstraintLayoutHome = binding.constraintLayoutHome
        recyclerViewHome = binding.recyclerViewHome

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mConnectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        setHasOptionsMenu(true)
    }

    fun getSearchResult(word: String) {
        viewModel.getSearchResult(word, requireContext())
        viewModel.videoModelList.observe(viewLifecycleOwner) {
            setAdapter(it)
        }
    }

    fun setAdapter(searchVideosList: List<VideoModel>) {
        context?.let {
            youtubeSearchAdapter = YoutubeSearchAdapter(
                it
            )
            recyclerViewHome.adapter = youtubeSearchAdapter
            youtubeSearchAdapter.submitList(searchVideosList)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.action_menu, menu)

        val mMenuItem: MenuItem = menu.findItem(R.id.action_search)

        val mSearchView: androidx.appcompat.widget.SearchView =
            mMenuItem.actionView as androidx.appcompat.widget.SearchView
        mSearchView.queryHint = getString(R.string.text_string_search_youtune_videos)
        mSearchView.imeOptions = EditorInfo.IME_ACTION_SEARCH

        mSearchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mSearchWord = query.toString()

                val imm: InputMethodManager? =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm?.hideSoftInputFromWindow(mConstraintLayoutHome.getWindowToken(), 0)

                if (isNetworkConnected()) {
                    getSearchResult(mSearchWord)
                } else {
                    Toast.makeText(
                        context,
                        getString(R.string.text_string_please_check_network),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mSearchWord = newText.toString()
                if (isNetworkConnected()) {
                        getSearchResult(mSearchWord)
                } else {
                    Toast.makeText(
                        context,
                        getString(R.string.text_string_please_check_network),
                        Toast.LENGTH_SHORT
                    ).show()
                }
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