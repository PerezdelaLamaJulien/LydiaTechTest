package com.jperez.lydia.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PaginationInfoEntity(
    @PrimaryKey @ColumnInfo(name = "id") val uid: String,
    @ColumnInfo(name = "seed") val seed: String,
    @ColumnInfo(name = "page") val page: Int,
    @ColumnInfo(name = "page_size") val pageSize: Int,
)