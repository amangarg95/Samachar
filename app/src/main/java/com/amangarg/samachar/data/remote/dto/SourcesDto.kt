package com.amangarg.samachar.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SourcesDto(
    @SerializedName("status")
    val status: String,

    @SerializedName("sources")
    val sources: List<SourceDto>
)