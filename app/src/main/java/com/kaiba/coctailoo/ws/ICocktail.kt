package com.kaiba.coctailoo.ws

import retrofit2.Call
import com.kaiba.coctailoo.model.Cocktail
import com.kaiba.coctailoo.model.CocktailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ICocktail {
    @GET("search.php")
    fun searchCocktails(@Query("s") cocktailName: String?): Call<CocktailResponse>
}