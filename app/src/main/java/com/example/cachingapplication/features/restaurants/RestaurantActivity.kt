package com.example.cachingapplication.features.restaurants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cachingapplication.databinding.ActivityRestaurantBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantActivity : AppCompatActivity() {

    //ref to viewmodel
    // -> provide viewmodel for activity -> activty recreate -> having the same viewmodel
    private val viewModel: RestaurantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val restaurantAdapter = RestaurantAdapter()

        binding.apply {
            rcvRestaurants.apply {
                adapter = restaurantAdapter
                layoutManager = LinearLayoutManager(this@RestaurantActivity)
            }

            //activity inactive (background) ->  liveData stop -> dont waste resource
            viewModel.restaurants.observe(this@RestaurantActivity) { restaurants ->
                restaurantAdapter.submitList(restaurants)
            }
        }
    }
}