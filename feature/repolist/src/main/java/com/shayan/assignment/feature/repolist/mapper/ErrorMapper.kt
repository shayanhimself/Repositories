package com.shayan.assignment.feature.repolist.mapper

import android.content.Context
import com.shayan.assignment.feature.repolist.R
import com.shayan.assignment.model.ErrorType
import com.shayan.assignment.model.GithubRepoModel
import com.shayan.assignment.model.Result

class ErrorMapper(private val appContext: Context) {
    fun map(result: Result<List<GithubRepoModel>>): String = when (result.errorType){
        ErrorType.CONNECTIVITY -> appContext.getString(R.string.connectivityIssue)
        ErrorType.GENERAL -> result.errorMessage ?: appContext.getString(R.string.unknownIssue)
        else -> appContext.getString(R.string.unknownIssue)
    }
}
