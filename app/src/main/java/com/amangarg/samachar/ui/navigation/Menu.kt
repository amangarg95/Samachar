package com.amangarg.samachar.ui.navigation

enum class Menu(val route: String, val displayName: String) {
    TOP_HEADLINES(route = "top_headlines", displayName = "Top Headlines"),
    SEARCH(route = "search", displayName = "Search"),
    BOOKMARKS(route = "bookmarks", displayName = "Bookmarks"),
    FILTERS(route = "filters", displayName = "Filters"),
    ARTICLE_DETAILS(route = "article_details", displayName = "Article Details")
}