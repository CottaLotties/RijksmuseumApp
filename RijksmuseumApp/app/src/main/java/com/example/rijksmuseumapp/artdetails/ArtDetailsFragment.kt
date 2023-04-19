package com.example.rijksmuseumapp.artdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.rijksmuseumapp.R
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtDetailsFragment : Fragment() {

    private val artDetailsViewModel: ArtDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_art_details, container, false)

        val titleTextView = view.findViewById<TextView>(R.id.artDetailsTitleTextView)
        val imageView = view.findViewById<ImageView>(R.id.artDetailsImageView)
        val descriptionTextView = view.findViewById<TextView>(R.id.descriptionTextView)
        val progress = view.findViewById<ProgressBar>(R.id.artDetailsProgressBar)

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            val artObjectTitle = arguments?.getString("artObjectId")
            artDetailsViewModel.getArtObjectDetails(artObjectTitle.toString())
            artDetailsViewModel.artObjectDetails.observe(viewLifecycleOwner) { artObjectDetails ->

                titleTextView.text =
                    artObjectDetails.title + " by " + artObjectDetails.principalOrFirstMaker
                descriptionTextView.text = artObjectDetails.description
                Picasso.get().load(artObjectDetails.webImage?.url).resize(
                    artObjectDetails.webImage?.width?.div(2) ?: 0, // width
                    artObjectDetails.webImage?.height?.div(2) ?: 0 // height
                ).into(imageView)
                progress.visibility = View.GONE
            }
        }
        return view
    }
}