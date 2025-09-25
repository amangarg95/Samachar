package com.amangarg.samachar.data.local.database.entity

import androidx.room.ColumnInfo

data class SourceEntity(
    @ColumnInfo("source_id")
    val id: String?,

    @ColumnInfo("source_name")
    val name: String?
)
