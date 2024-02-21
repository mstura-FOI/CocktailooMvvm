package com.kaiba.coctailoo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kaiba.coctailoo.ui.screens.SearchCocktail.SearchCocktail
import com.kaiba.coctailoo.ui.screens.SearchCocktail.SearchCocktailViewModel
import com.kaiba.coctailoo.ui.theme.CoctailooTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val SearchCocktailViewModel by viewModels<SearchCocktailViewModel>()
        setContent {
            CoctailooTheme {
            SearchCocktail(viewModel = SearchCocktailViewModel)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoctailooTheme {

    }
}