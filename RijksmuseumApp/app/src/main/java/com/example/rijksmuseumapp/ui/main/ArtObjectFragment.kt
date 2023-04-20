package com.example.rijksmuseumapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rijksmuseumapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtObjectFragment : Fragment(), ArtObjectAdapter.OnArtObjectClickListener {

    private val mainViewModel: ArtObjectViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArtObjectAdapter
    private lateinit var progressBar: ProgressBar
    private var isLoading = false
    private var isLastPage = false
    private var currentPage = 1
    private val pageSize = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        adapter = ArtObjectAdapter(this)
        recyclerView.adapter = adapter

        progressBar = view.findViewById(R.id.progress_bar)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && !isLastPage) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= pageSize
                    ) {
                        currentPage++
                        mainViewModel.fetchDataFromRepository({ artObjects ->
                            adapter.addData(artObjects)
                        }, { errorMessage ->
                            onError(errorMessage)
                        })
                    }
                }
            }
        })

        progressBar.visibility = View.VISIBLE
        mainViewModel.fetchDataFromRepository({ artObjects ->
            adapter.addData(artObjects)
            activity?.runOnUiThread {
                progressBar.visibility = View.GONE // Hide progress bar after data is loaded
            }
        }, { errorMessage ->
            onError(errorMessage)
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.visibility = View.VISIBLE
        mainViewModel.fetchDataFromRepository({ artObjects ->
            adapter.addData(artObjects)
            activity?.runOnUiThread {
                progressBar.visibility = View.GONE // Hide progress bar after data is loaded
            }
        }, { errorMessage ->
            onError(errorMessage)
        })
    }

    override fun onArtObjectClicked(artObjectId: String) {
        findNavController().navigate(
            R.id.action_mainFragment_to_artDetailsFragment,
            bundleOf("artObjectId" to artObjectId)
        )
    }

    fun onError(e: Throwable) {
        val message = e.message ?: "An unknown error occurred"
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
            progressBar.visibility = View.GONE
        }
    }
}