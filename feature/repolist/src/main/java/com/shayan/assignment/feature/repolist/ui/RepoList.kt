package com.shayan.assignment.feature.repolist.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.shayan.assignment.designsystem.MyTheme

@Composable
fun RepoList(){
    LazyColumn{
        item {
            Text(text = "Hello world")
        }
        item {
            Text(text = "Hello world 2")
        }
        item {
            Text(text = "Hello world 3")
        }
    }
}

@Preview
@Composable
fun RepoListPreview(){
    MyTheme {
        RepoList()
    }
}
