package com.shayan.assignment.feature.repolist.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.shayan.assignment.designsystem.MyTheme

@Composable
fun RepoList(){
    val repoItems = listOf(1, 2, 3, 4, 5, 6)
    LazyColumn{
        repoItems.forEach {
            item {
                RepoRow(num = it)
            }
        }
    }
}

@Composable
fun RepoRow(num: Int) {
    Text(text = "Hello world $num")
}

@Preview
@Composable
fun RepoListPreview(){
    MyTheme {
        RepoList()
    }
}
