package com.example.chefpal.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chefpal.*
import com.example.chefpal.databinding.FragmentFavoritesBinding
import com.example.chefpal.ui.card.CardClickListener

class FavoritesFragment : Fragment(), CardClickListener {

    private var _binding: FragmentFavoritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val favoritesViewModel =
            ViewModelProvider(this).get(FavoritesViewModel::class.java)

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val favoritesFragment = this
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CardAdapter(favoritesList, favoritesFragment)
        }

        val noRecipePlaceHolder = binding.noRecipePlaceholder

        if (favoritesList.size == 0)
            noRecipePlaceHolder.visibility = View.VISIBLE
        else
            noRecipePlaceHolder.visibility = View.GONE

        return root
    }

    override fun onClick(recipe: Recipe) {
        curRecipe = recipe
        navView!!.menu.findItem(R.id.navigation_recipe).isEnabled = true
        findNavController().navigate(R.id.action_favoritesFragment_to_recipeFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}