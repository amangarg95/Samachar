package com.amangarg.samachar.ui.composable.screen.filters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amangarg.samachar.domain.model.Country
import com.amangarg.samachar.domain.model.Language
import com.amangarg.samachar.domain.model.Source
import com.amangarg.samachar.ui.UiState
import com.amangarg.samachar.ui.theme.VintageBackground
import com.amangarg.samachar.ui.composable.screen.bar.FilterBar
import com.amangarg.samachar.ui.model.FilterCategory
import com.amangarg.samachar.ui.viewmodel.FiltersScreenViewModel

@Composable
fun FiltersScreen(
    countryList: List<Country>,
    languageList: List<Language>,
    onCountryToggle: (Country) -> Unit,
    onLanguageToggle: (Language) -> Unit,
    filtersViewModel: FiltersScreenViewModel
) {
    var selectedFilterCategory by rememberSaveable { mutableStateOf(FilterCategory.COUNTRY) }
    val vintageBg = VintageBackground
    val vintageBorder = Color(0xFF4E3629)
    val vintageText = Color(0xFF222222)
    val sourcesState by filtersViewModel.sourcesState.collectAsStateWithLifecycle()

    LaunchedEffect(selectedFilterCategory) {
        if (selectedFilterCategory == FilterCategory.SOURCE) {
            filtersViewModel.fetchSources()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(vintageBg)
            .padding(12.dp)
    ) {
        FilterBar(title = "Filters")

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(0.3F)
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp)
            ) {
                items(FilterCategory.entries.toTypedArray()) { category ->
                    FilterItem(
                        modifier = Modifier.wrapContentWidth(),
                        category = category,
                        isSelected = selectedFilterCategory == category,
                        onClick = { selectedFilterCategory = category }
                    )
                }
            }

            VerticalDivider(thickness = 0.5.dp, color = vintageBorder)

            when (selectedFilterCategory) {
                FilterCategory.COUNTRY -> LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(0.7F)
                        .fillMaxHeight()
                ) {
                    items(countryList) { country ->
                        Text(
                            text = country.name,
                            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                            color = vintageText,
                            modifier = Modifier
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                                .clickable {
                                    onCountryToggle(country)
                                }
                        )
                    }
                }

                FilterCategory.LANGUAGE -> LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally, // centers children if they don’t fill max width
                    modifier = Modifier
                        .weight(0.7F)
                        .fillMaxHeight()
                ) {
                    items(languageList) { language ->
                        Text(
                            text = language.name,
                            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                            color = vintageText,
                            modifier = Modifier
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                                .clickable {
                                    onLanguageToggle(language)
                                }
                        )
                    }
                }

                FilterCategory.SOURCE -> {
                    when (sourcesState) {
                        is UiState.Loading -> {
                            Box(
                                modifier = Modifier
                                    .weight(0.7f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }

                        is UiState.Success -> {
                            val sources = (sourcesState as UiState.Success<List<Source>>).data
                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(0.7f)
                                    .fillMaxHeight()
                            ) {
                                items(sources) { source ->
                                    Text(
                                        text = source.name,
                                        fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                                        color = vintageText,
                                        modifier = Modifier
                                            .padding(horizontal = 4.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }

                        is UiState.Error -> {
                            val message = (sourcesState as UiState.Error).message
                            Box(
                                modifier = Modifier
                                    .weight(0.7f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Error: $message",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }

                        else -> {
                        }
                    }
                }
            }
        }
    }
}