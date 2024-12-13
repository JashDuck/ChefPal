package com.example.chefpal.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.chefpal.*
import com.example.chefpal.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {

    private var _binding: FragmentRecipeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)

        val recipeViewModel =
            ViewModelProvider(this).get(RecipeViewModel::class.java)

        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val favoriteButton: Button = binding.favoriteButton

        if (curRecipe != null) {
            binding.name.text = curRecipe!!.name
            binding.description.text = curRecipe!!.description
            binding.recipe.text = curRecipe!!.recipe
            setFavorite(favoriteButton)
        }

        favoriteButton.setOnClickListener {
            curRecipe?.favorite = !curRecipe?.favorite!!
            setFavorite(favoriteButton)
        }

        return root
    }

    private fun setFavorite(button: Button) {
        if (curRecipe!!.favorite) {
            button.text = getString(R.string.unfavorite_button_text)
            if (curRecipe !in favoritesList)
                favoritesList.add(favoritesList.size, curRecipe!!)
        } else {
            button.text = getString(R.string.favorite_button_text)
            favoritesList.remove(curRecipe)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}