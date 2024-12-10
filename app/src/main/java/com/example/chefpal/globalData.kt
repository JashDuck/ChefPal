package com.example.chefpal

import com.google.android.material.bottomnavigation.BottomNavigationView

var navView: BottomNavigationView? = null

var curRecipe: Recipe? = null
val favoritesList: MutableList<Recipe> = mutableListOf()
val recipeList: MutableList<Recipe> = mutableListOf()

data class Recipe (
    val name: String,
    val description: String,
    val recipe: String,
    var favorite: Boolean,
    val id: Int? = recipeList.size
)
