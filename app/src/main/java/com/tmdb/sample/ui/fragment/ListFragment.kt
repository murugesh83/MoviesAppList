package com.tmdb.sample.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tmdb.sample.R
import com.tmdb.sample.data.PopularItem
import com.tmdb.sample.module.ItemClick
import com.tmdb.sample.ui.adapter.ListItemAdapter
import com.tmdb.sample.ui.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class ListFragment : Fragment(), ItemClick {

    private val TAG = ListFragment::class.java.simpleName
    private val listViewModel: ListViewModel by activityViewModels()

    @Inject
    lateinit var listItemAdapter: ListItemAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressbar: ProgressBar
    private var observer = Observer<List<PopularItem>>() {
        progressbar.visibility = View.GONE
        listItemAdapter.setList(it as ArrayList<PopularItem>)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        listViewModel.mLiveData.observe(this.requireActivity(), observer)
        listItemAdapter.setCallBack(this)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressbar = view.findViewById(R.id.progressbar)
        recyclerView = view.findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.adapter = listItemAdapter
        if (listItemAdapter.itemCount == 0)
            loadList()

        view.findViewById<Button>(R.id.more).setOnClickListener {
            loadList()
        }
    }

    private fun loadList() {
        lifecycleScope.launch {
            progressbar.visibility = View.VISIBLE
            listViewModel.getPopularData()
        }
    }

    override fun onItemClicked(popularItem: PopularItem) {
        Log.d(TAG, "load details  $popularItem.id")
        var bundle = Bundle()
        bundle.putString("mid", popularItem.id)
        findNavController().navigate(R.id.action_ListFragment_to_DetailsFragment, bundle)
    }
}