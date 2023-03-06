package com.example.youtubesearch.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.youtubesearch.R
import com.youtubesearch.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var mainBottomNavigation: BottomNavigationView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavController =
            (childFragmentManager.findFragmentById(R.id.mainContainerView) as NavHostFragment)
                .navController

        mainBottomNavigation = binding.mainBottomNavigationView
        mainBottomNavigation.setupWithNavController(bottomNavController)

        mainBottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.homeFragment -> {
                    findNavController().navigate(R.id.nav_saveFragment_to_homeFragment)
                    true
                }
                R.id.saveFragment -> {
                    findNavController().navigate(R.id.nav_homeFragment_to_saveFragment)
                    true
                }
                else -> false
            }
        }
    }
}