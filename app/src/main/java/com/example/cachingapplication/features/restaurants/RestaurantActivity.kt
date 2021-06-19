package com.example.cachingapplication.features.restaurants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cachingapplication.databinding.ActivityRestaurantBinding
import com.example.cachingapplication.utils.Resource
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
            viewModel.restaurants.observe(this@RestaurantActivity) { resource ->
                restaurantAdapter.submitList(resource.data)
                progressBar.isVisible =
                    resource is Resource.Loading && resource.data.isNullOrEmpty()
                tvErrorMessage.isVisible =
                    resource is Resource.Loading && resource.data.isNullOrEmpty()
                tvErrorMessage.text = resource.error?.localizedMessage
            }
        }
    }
}