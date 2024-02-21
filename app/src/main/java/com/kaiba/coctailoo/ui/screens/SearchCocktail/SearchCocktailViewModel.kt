package com.kaiba.coctailoo.ui.screens.SearchCocktail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaiba.coctailoo.model.CocktailResponse
import com.kaiba.coctailoo.ws.ICocktail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCocktailViewModel @Inject constructor(val CocktailWs: ICocktail): ViewModel() {
    var searchText = mutableStateOf("")
    var data = mutableStateOf<CocktailResponse?>(null)
    fun getData(text: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = CocktailWs.searchCocktails(text).execute()
                Log.d("gee",response.toString())
                if (response.isSuccessful) {
                    data.value = response.body()
                } else {
                    // Handle unsuccessful response
                }
            } catch (e: Exception) {
                Log.d("err","${e.message}")
            }
        }

    }
}