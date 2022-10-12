package com.shayan.assignment.feature.repolist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.shayan.assignment.feature.repolist.databinding.FragmentListBinding
import com.shayan.assignment.feature.repolist.viewadapter.RepoListAdapter
import com.shayan.assignment.feature.repolist.viewmodel.RepoListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoListFragment : Fragment() {

    private val viewModel: RepoListViewModel by viewModel()

    private lateinit var binding: FragmentListBinding

    private val adapter = RepoListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        binding.reposRecyclerView.adapter = adapter
        observeListings()
        return binding.root
    }

    private fun observeListings() {
        lifecycleScope.launch {
            viewModel.repos.collectLatest {
                adapter.updateList(it)
            }
        }
    }

}
