package com.kaiba.coctailoo.ui.screens.SearchCocktail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch

@Composable
fun SearchCocktail(viewModel: SearchCocktailViewModel){

    Column {
        var courutine = rememberCoroutineScope()
        TextField(value = viewModel.searchText.value, onValueChange = {viewModel.searchText.value = it} )
        Button(onClick = { courutine.launch {
            viewModel.getData(viewModel.searchText.value)
        } }) {
            Text(text = "Search")
        }
        LazyColumn(content = {
            try {
                viewModel.data.value?.let {
                    items(it.drinks){
                        Text(text = it.strDrink)
                    }
                }
            }catch (err:Exception){

            }

        })
    }
}