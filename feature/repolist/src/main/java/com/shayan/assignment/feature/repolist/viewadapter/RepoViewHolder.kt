package com.shayan.assignment.feature.repolist.viewadapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.shayan.assignment.designsystem.load
import com.shayan.assignment.feature.repolist.R
import com.shayan.assignment.feature.repolist.databinding.RepoListItemBinding
import com.shayan.assignment.model.GithubRepoModel

class RepoViewHolder(private val binding: RepoListItemBinding) : ViewHolder(binding.root) {

    fun bind(repo: GithubRepoModel) {
        binding.repoTitleText.text = repo.name
        binding.visibilityText.text = repo.visibility
        binding.isPrivateText.setText(repo.isPrivate.toYesNoString())
        binding.avatarImage.load(repo.ownerAvatarUrl)
    }

    private fun Boolean.toYesNoString(): Int = if (this) R.string.yes else R.string.no
}
