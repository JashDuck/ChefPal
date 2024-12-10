package com.example.chefpal.ui.card

import androidx.recyclerview.widget.RecyclerView
import com.example.chefpal.databinding.CardCellBinding
import com.example.chefpal.Recipe

class CardViewHolder(
    private val cardCellBinding: CardCellBinding,
    private val clickListener: CardClickListener
) : RecyclerView.ViewHolder(cardCellBinding.root)
{
    fun bindRecipe(recipe: Recipe) {
        cardCellBinding.name.text = recipe.name
        cardCellBinding.description.text = recipe.description

        cardCellBinding.cardView.setOnClickListener {
            clickListener.onClick(recipe)
        }
    }
}