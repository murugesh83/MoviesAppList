package com.tmdb.sample.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import com.tmdb.sample.R
import com.tmdb.sample.data.MovieDetails
import com.tmdb.sample.ui.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val s = DetailsFragment::class.java.simpleName
    lateinit var id: String

    @Inject
    lateinit var picasso: Picasso
    private val detailsViewModel: DetailsViewModel by activityViewModels()
    var observer: Observer<MovieDetails> = Observer<MovieDetails>() {
        setDetails(it)
    }

    lateinit var imageView: ImageView
    lateinit var titleTextView: TextView
    lateinit var overviewTextView: TextView
    lateinit var revenueTextView: TextView
    lateinit var budgetTextView: TextView
    lateinit var releaseDateTextView: TextView
    lateinit var runtimeTextView: TextView
    lateinit var progressBar: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //detailsViewModel.detailsLiveData.removeObserver(observer)
        detailsViewModel.detailsLiveData.observe(viewLifecycleOwner, observer)
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bundle = arguments
        if (bundle != null) {
            id = bundle.getString("mid", "")
        }
        imageView = view.findViewById(R.id.details_imageView)
        titleTextView = view.findViewById(R.id.details_title)
        overviewTextView = view.findViewById(R.id.details_overview)
        revenueTextView = view.findViewById(R.id.details_revenue)
        budgetTextView = view.findViewById(R.id.details_budget)
        releaseDateTextView = view.findViewById(R.id.details_release)
        runtimeTextView = view.findViewById(R.id.details_runtime)
        progressBar = view.findViewById(R.id.progressbar)
        progressBar.visibility = View.VISIBLE
        lifecycleScope.launchWhenResumed { detailsViewModel.loadMovieDetails(id) }
    }

    private fun setDetails(movieDetails: MovieDetails) {
        if (movieDetails != null && movieDetails.id == id) {
            progressBar.visibility = View.GONE
            Log.d(s, "setDetails  : ${movieDetails.title}")
            picasso.load("https://image.tmdb.org/t/p/original/${movieDetails.backdropPath}")
                .into(imageView)
            titleTextView.text = movieDetails.title
            overviewTextView.text = movieDetails.overview
            revenueTextView.append("  ${movieDetails.revenue}")
            budgetTextView.append("  ${movieDetails.budget}")
            releaseDateTextView.append("  ${movieDetails.releaseDate}")
            runtimeTextView.append(" ${movieDetails.runtime}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(s, "onDestroy")
    }
}