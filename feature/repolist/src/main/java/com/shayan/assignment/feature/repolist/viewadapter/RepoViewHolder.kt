package com.shayan.assignment.feature.repolist.viewadapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.shayan.assignment.designsystem.load
import com.shayan.assignment.feature.repolist.R
import com.shayan.assignment.feature.repolist.databinding.RepoListItemBinding
import com.shayan.assignment.feature.repolist.utils.toYesNoString
import com.shayan.assignment.feature.repolist.view.OnRepoClickListener
import com.shayan.assignment.model.GithubRepoModel

class RepoViewHolder(
    private val binding: RepoListItemBinding,
    private val onRepoClickListener: OnRepoClickListener
) : ViewHolder(binding.root) {

    private var repoId: Int = 0

    init {
        binding.root.setOnClickListener {
            onRepoClickListener.onRepoItemClick(repoId)
        }
    }

    fun bind(repo: GithubRepoModel) {
        repoId = repo.id
        binding.repoTitleText.text = repo.name
        binding.visibilityText.text = repo.visibility
        binding.isPrivateText.setText(repo.isPrivate.toYesNoString())
        binding.avatarImage.load(repo.ownerAvatarUrl)
    }

}
