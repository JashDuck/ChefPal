package com.example.chefpal.ui.card

import com.example.chefpal.Recipe

interface CardClickListener {
    fun onClick(recipe: Recipe)
}