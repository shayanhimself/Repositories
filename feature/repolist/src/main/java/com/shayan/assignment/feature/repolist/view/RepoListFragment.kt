package com.shayan.assignment.feature.repolist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.shayan.assignment.feature.repolist.R
import com.shayan.assignment.feature.repolist.databinding.FragmentRepoListBinding
import com.shayan.assignment.feature.repolist.view.RepoDetailFragment.Companion.ARG_REPO_ID
import com.shayan.assignment.feature.repolist.viewadapter.RepoListAdapter
import com.shayan.assignment.feature.repolist.viewmodel.RepoListViewModel
import com.shayan.assignment.feature.repolist.viewmodel.RepoListViewModel.ViewAction
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.max

class RepoListFragment : Fragment() {

    private val viewModel: RepoListViewModel by viewModel()

    private lateinit var binding: FragmentRepoListBinding

    private val onRepoClickListener = object : OnRepoClickListener {
        override fun onRepoItemClick(repoId: Int) {
            val bundle = bundleOf(ARG_REPO_ID to repoId)
            findNavController().navigate(R.id.action_list_to_detail, bundle)
        }
    }

    private val adapter = RepoListAdapter(onRepoClickListener)
    private lateinit var linearLayoutManager : LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepoListBinding.inflate(inflater, container, false)
        initRecyclerView()
        observeListings()
        observeError()
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

    private fun observeError() {
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Snackbar.make(
                binding.swipeRefreshLayout,
                it,
                Snackbar.LENGTH_SHORT
            ).show()
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
