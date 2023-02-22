package com.example.youtubesearch.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.youtubesearch.R
import com.youtubesearch.databinding.ActivityMainBinding
import com.youtubesearch.databinding.FragmentHomeBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}