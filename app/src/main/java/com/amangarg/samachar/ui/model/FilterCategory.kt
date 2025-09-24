package com.amangarg.samachar.ui.model

import com.amangarg.samachar.R

enum class FilterCategory(val displayName: String, val icon: Int) {
    COUNTRY(displayName = "Country", icon = R.drawable.ic_region),
    LANGUAGE(displayName = "Language", icon = R.drawable.ic_language),
    SOURCE(displayName = "Source", icon = R.drawable.ic_source)
}