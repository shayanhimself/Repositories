package com.shayan.assignment.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class GithubRepoEntity(
    @PrimaryKey var id: Int = 0,
    @ColumnInfo var name: String = "",
    @ColumnInfo var fullName: String = "",
    @ColumnInfo var private: Boolean = false,
    @ColumnInfo var ownerAvatarUrl: String = "",
    @ColumnInfo var visibility: String = "",
    @ColumnInfo var page: Int = 0,
    @ColumnInfo var order: Int = 0,
)
//TODO: convert to immutable
