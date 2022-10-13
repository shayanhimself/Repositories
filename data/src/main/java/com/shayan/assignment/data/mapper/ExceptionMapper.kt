package com.shayan.assignment.data.mapper

import com.shayan.assignment.model.ErrorType
import java.io.IOException

internal fun Exception?.toErrorType() = when(this) {
    is IOException -> ErrorType.CONNECTIVITY
    else -> ErrorType.GENERAL
}
