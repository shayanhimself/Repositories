package com.shayan.assignment.feature.repolist.utils

import com.shayan.assignment.feature.repolist.R

internal fun Boolean.toYesNoString(): Int = if (this) R.string.yes else R.string.no
