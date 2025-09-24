package com.amangarg.samachar.ui.activity

import com.amangarg.samachar.util.AppConstants

data class MainUiState(
    val currentRegion: String = AppConstants.DEFAULT_COUNTRY,
    val currentLanguage: String = AppConstants.DEFAULT_LANGUAGE,
    val date: String = ""
)