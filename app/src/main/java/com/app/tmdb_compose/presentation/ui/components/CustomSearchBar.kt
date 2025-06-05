package com.app.tmdb_compose.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.tmdb_compose.presentation.ui.theme.SearchBarBackgroundColor
import com.app.tmdb_compose.presentation.ui.theme.TextColorSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    onSearchChanged: (String) -> Unit,
    onSearchClicked: () -> Unit // Callback for when search icon/bar is meaningfully interacted with
) {
    var searchText by remember { mutableStateOf("") }
    OutlinedTextField(
        value = searchText,
        onValueChange = {
            searchText = it
            onSearchChanged(it) // Continuous search query update
        },
        placeholder = { Text("Search movies, tv shows...", color = TextColorSecondary) },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onSearchClicked() }, // Make the whole bar clickable for navigation
        shape = RoundedCornerShape(28.dp), // More rounded
        leadingIcon = {
            Icon(
                Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = TextColorSecondary
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent, // Cleaner look
            unfocusedBorderColor = Color.Transparent, // Cleaner look
            focusedContainerColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surfaceColorAtElevation(
                4.dp
            ) else SearchBarBackgroundColor,
            unfocusedContainerColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surfaceColorAtElevation(
                4.dp
            ) else SearchBarBackgroundColor,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            disabledBorderColor = Color.Transparent,
            disabledContainerColor = SearchBarBackgroundColor,

            ),
        singleLine = true,
        // readOnly = true, // To make it act more like a button before navigating
        // enabled = false // To make it act more like a button
    )
}