
package com.example.youtubesearch.presentation.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubesearch.domain.models.VideoModel
import com.example.youtubesearch.presentation.MainViewModel
import com.example.youtubesearch.presentation.adapters.searchadapter.YoutubeSearchAdapter
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
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(requireActivity().application) as T
            }
        }).get(MainViewModel::class.java)

        viewModel.videoModelList.observe(viewLifecycleOwner) {
            setAdapter(it)
        }
    }

    fun getSearchResult(word: String) {
        viewModel.getSearchResult(word)
    }

    private fun setAdapter(searchVideosList: List<VideoModel>) {
        youtubeSearchAdapter = YoutubeSearchAdapter(viewModel)
        recyclerViewHome.adapter = youtubeSearchAdapter
        youtubeSearchAdapter.submitList(searchVideosList)
        youtubeSearchAdapter.onItemClickListener = {
            val bundle = bundleOf("videoId" to it.id.videoId)
            findNavController().navigate(R.id.nav_homeFragment_to_showVideoFragment, bundle)
        }
        setupSwipeListener(recyclerViewHome)
    }

    private fun setupSwipeListener(rvShopList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = youtubeSearchAdapter.currentList[viewHolder.adapterPosition]
                viewModel.hideVideo(item, viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
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

    private fun searchVideos() {
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchWord = query.toString()

                val imm: InputMethodManager? =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm?.hideSoftInputFromWindow(mConstraintLayoutHome.windowToken, 0)

                if (isNetworkConnected()) {
                getSearchResult(searchWord)
                } else {
                    Toast.makeText(
                        context,
                        getString(R.string.text_string_please_check_network),
                        Toast.LENGTH_SHORT
                    ).show()
                }
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
        return mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)?.state == NetworkInfo.State.CONNECTED ||
                mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)?.state == NetworkInfo.State.CONNECTED
    }
}