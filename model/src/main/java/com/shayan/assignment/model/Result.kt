package com.shayan.assignment.model

data class Result<out T> (
    val data: T,
    val status: ResultStatus,
    val errorMessage: String? = null,
    val errorType: ErrorType? = null,
    val isLastPage: Boolean = false,
)
