package com.shayan.assignment.model

data class Result<out T> (
    val data: T,
    val status: ResultStatus,
    val isLastPage: Boolean,
    val errorMessage: String? = null,
    val errorType: ErrorType? = null,
)
