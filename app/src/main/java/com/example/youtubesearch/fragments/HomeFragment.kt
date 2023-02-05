package com.example.youtubesearch.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubesearch.adapters.YoutubeSearchAdapter
import com.example.youtubesearch.models.VideoModel
import com.example.youtubesearch.network.APIClient
import com.example.youtubesearch.network.APIClient.API_KEY
import com.youtubesearch.R
import com.youtubesearch.databinding.FragmentHomeBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


class HomeFragment : Fragment(/*R.layout.fragment_home*/) {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var mConstraintLayoutHome: ConstraintLayout

    private lateinit var mYoutubeSearchAdapter: YoutubeSearchAdapter
    private lateinit var mRecyclerViewHome: RecyclerView

    private var mVideoModelList: ArrayList<VideoModel> = ArrayList()

    var mSearchWord: String = ""
    private lateinit var mConnectivityManager: ConnectivityManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        mConstraintLayoutHome = binding.constraintLayoutHome
        mRecyclerViewHome = binding.recyclerViewHome
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mConnectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        setHasOptionsMenu(true)
    }

    fun getSearchResult(word: String) {
        if (!word.equals("")) {
            context?.let {
                APIClient.instance.searchVideo(
                    API_KEY,
                    word
                ).enqueue(object : Callback<ResponseBody> {
//                    override fun onResponse(
//                        call: Call<ResponseModel>,
//                        response: Response<ResponseModel>
//                    ) {
//
//                        if (response.isSuccessful) {
//                            val mResponseModel: ResponseModel? = response.body()
//                            if (mResponseModel != null) {
//                                val mErrorModel: ErrorModel? = mResponseModel.error
//                                if (mErrorModel != null) {
//                                    Toast.makeText(it, mErrorModel.message, Toast.LENGTH_SHORT)
//                                        .show()
//                                } else {
//                                    mVideoModelList.addAll(mResponseModel.items)
//                                    setAdapter(mVideoModelList)
//                                }
//
//                            } else {
//                                Toast.makeText(
//                                    it,
//                                    getString(R.string.text_string_no_data_found),
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            }
//                        }
//                    }


                    //                    override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
//                        Toast.makeText(it, t.message, Toast.LENGTH_SHORT)
//                            .show()
//                    }
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            val responseJsonObject = response.body()?.string()
                            parseModels(responseJsonObject)
//                            mVideoModelList.addAll(parseModels(responseJsonObject))

                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                    }
                })
            }
        }
    }

    fun setAdapter(mSearchaVideosList: ArrayList<VideoModel>) {
        context?.let {
            mYoutubeSearchAdapter = YoutubeSearchAdapter(
                it
            )
            mRecyclerViewHome.adapter = mYoutubeSearchAdapter
            mYoutubeSearchAdapter.submitList(mSearchaVideosList)
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
                    if (mSearchWord.length >= 3) {
                        getSearchResult(mSearchWord)
                    } else {
                        context?.let {
                            mVideoModelList.clear()
                            setAdapter(mVideoModelList)
                        }
                    }
                } else {
                    Toast.makeText(
                        context,
                        getString(R.string.text_string_please_check_network),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                return true
            }

        })
    }

    fun isNetworkConnected(): Boolean {
        if (
            mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).state == NetworkInfo.State.CONNECTED ||
            mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).state == NetworkInfo.State.CONNECTED
        ) {
            return true
        } else {
            return false
        }
    }
}

    fun parseModels(response: String?) {

        val pattern = Regex("""^.*"items":\s*\[(.*)\].*$""")
        val result = pattern.find(response.toString())?.groupValues?.get(1)
        Log.i("TAGresult", "$result + $response")
//        val patternUrl = """https://i.ytimg.com/vi/\w+/hqdefault.jpg"""
//        val url = Regex(patternUrl).find(response.toString())?.value
//
//        val patternId = """"videoId":\s*"(\w+)"""
//        val videoId = Regex(pattern).find(response.toString())?.groupValues?.get(1)
    }