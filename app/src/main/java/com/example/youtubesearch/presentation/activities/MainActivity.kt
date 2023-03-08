package com.example.youtubesearch.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.youtubesearch.R
import com.youtubesearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainBottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavController = findNavController(R.id.navHostFragmentContainer)

        mainBottomNavigation = binding.mainBottomNavigationView
        mainBottomNavigation.setupWithNavController(bottomNavController)

        mainBottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    bottomNavController.navigate(R.id.homeFragment)
                    true
                }
                R.id.saveFragment -> {
                    bottomNavController.navigate(R.id.saveFragment)
                    true
                }
                else -> false
            }
        }
    }
}