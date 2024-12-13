package com.example.chefpal.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chefpal.*
import com.example.chefpal.databinding.FragmentHomeBinding
import com.example.chefpal.ui.card.CardClickListener

class HomeFragment : Fragment(), CardClickListener {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val homeFragment = this
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CardAdapter(recipeList, homeFragment)
        }

        val ingredientsButton: Button = binding.ingredientsButton

        ingredientsButton.setOnClickListener {
            activity?.let { it1 -> homeViewModel.generateRecipes(binding, it1,1) }
            binding.recyclerView.adapter?.notifyItemInserted(recipeList.size - 1)
        }

        return root
    }

    override fun onClick(recipe: Recipe) {
        curRecipe = recipe
        navView!!.menu.findItem(R.id.navigation_recipe).isEnabled = true
        findNavController().navigate(R.id.action_homeFragment_to_recipeFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}