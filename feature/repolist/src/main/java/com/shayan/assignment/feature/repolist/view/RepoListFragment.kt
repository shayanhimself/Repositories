package com.shayan.assignment.feature.repolist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shayan.assignment.feature.repolist.databinding.FragmentListBinding
import com.shayan.assignment.feature.repolist.viewadapter.RepoListAdapter
import com.shayan.assignment.feature.repolist.viewmodel.RepoListViewModel
import com.shayan.assignment.feature.repolist.viewmodel.RepoListViewModel.ViewAction
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.max

class RepoListFragment : Fragment() {

    private val viewModel: RepoListViewModel by viewModel()

    private lateinit var binding: FragmentListBinding

    private val adapter = RepoListAdapter()
    private lateinit var linearLayoutManager : LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        initRecyclerView()
        observeListings()
        return binding.root
    }

    private fun initRecyclerView() = with(binding) {
        linearLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        reposRecyclerView.layoutManager = linearLayoutManager
        reposRecyclerView.adapter = adapter
        reposRecyclerView.addOnScrollListener(endOfListScrollListener)

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.perform(ViewAction.OnSwipeToRefresh)
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun observeListings() {
        viewModel.reposLiveData.observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }
    }

    private val endOfListScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (isNearEndOfList()) {
                viewModel.perform(ViewAction.OnEndOfListReached)
            }
        }
    }

    private fun isNearEndOfList(): Boolean {
        val threshold = max(adapter.itemCount - END_OF_LIST_OFFSET, 0)
        return threshold <= linearLayoutManager.findLastCompletelyVisibleItemPosition()
    }

    companion object {
        const val END_OF_LIST_OFFSET = 5
    }
}
