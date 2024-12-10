package com.example.chefpal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chefpal.databinding.CardCellBinding
import com.example.chefpal.ui.card.CardViewHolder
import com.example.chefpal.ui.card.CardClickListener

class CardAdapter(
    private val recipes: List<Recipe>,
    private val clickListener: CardClickListener
) : RecyclerView.Adapter<CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder
    {
        val from = LayoutInflater.from(parent.context)
        val binding = CardCellBinding.inflate(from, parent, false)
        return CardViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int)
    {
        holder.bindRecipe(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

}