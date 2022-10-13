package com.shayan.assignment.feature.repolist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shayan.assignment.designsystem.load
import com.shayan.assignment.feature.repolist.databinding.FragmentRepoDetailBinding
import com.shayan.assignment.feature.repolist.utils.toYesNoString
import com.shayan.assignment.feature.repolist.viewmodel.RepoDetailViewModel
import com.shayan.assignment.model.GithubRepoModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RepoDetailFragment : Fragment() {

    private lateinit var binding: FragmentRepoDetailBinding

    private val viewModel: RepoDetailViewModel by viewModel { parametersOf(getRepoId()) }

    private fun getRepoId(): Int = arguments?.getInt(ARG_REPO_ID) ?: 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepoDetailBinding.inflate(inflater, container, false)
        observeData()
        return binding.root
    }

    private fun observeData() {
        viewModel.repoLiveData.observe(viewLifecycleOwner) {
            updateView(it)
        }
    }

    private fun updateView(repo: GithubRepoModel) = with(binding) {
        repoTitleText.text = repo.name
        fullNameText.text = repo.fullName
        visibilityText.text = repo.visibility
        descriptionText.text = repo.description
        urlText.text = repo.htmlUrl
        isPrivateText.setText(repo.isPrivate.toYesNoString())
        avatarImage.load(repo.ownerAvatarUrl)
    }

    companion object {
        const val ARG_REPO_ID = "repoId"
    }
}
