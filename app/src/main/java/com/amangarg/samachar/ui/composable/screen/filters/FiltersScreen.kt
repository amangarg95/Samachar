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
import com.amangarg.samachar.domain.model.Country
import com.amangarg.samachar.domain.model.Language
import com.amangarg.samachar.ui.theme.VintageBackground
import com.amangarg.samachar.ui.composable.screen.bar.FilterBar
import com.amangarg.samachar.ui.model.FilterCategory

@Composable
fun FiltersScreen(
    countryList: List<Country>,
    languageList: List<Language>,
    onCountryToggle: (Country) -> Unit,
    onLanguageToggle: (Language) -> Unit
) {
    var selected by rememberSaveable { mutableStateOf(FilterCategory.COUNTRY) }
    val vintageBg = VintageBackground
    val vintageBorder = Color(0xFF4E3629)
    val vintageText = Color(0xFF222222)

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
                .padding(vertical = 32.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(0.3F)
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp)
            ) {
                items(FilterCategory.entries.toTypedArray()) { category ->
                    FilterItem(
                        modifier = Modifier.weight(1f),
                        category = category,
                        isSelected = selected == category,
                        onClick = { selected = category }
                    )
                }
            }

            VerticalDivider(thickness = 0.5.dp, color = vintageBorder)

            when (selected) {
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

                FilterCategory.SOURCE -> LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment =
                        Alignment.CenterHorizontally, // centers children if they don’t fill max width
                    modifier = Modifier
                        .weight(0.7F)
                        .fillMaxHeight()
                ) {
                    items(countryList) { country ->
                        Text(
                            text = country.name,
                            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                            color = vintageText,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                        )
                    }
                }
            }


        }
    }
}