package com.example.cachingapplication.features.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cachingapplication.api.RestaurantApi
import com.example.cachingapplication.data.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    api: RestaurantApi
) : ViewModel() {

    private val restaurantsLiveData = MutableLiveData<List<Restaurant>>()

    //activity access -> just read, not change, only viewmodel can change
    val restaurants: LiveData<List<Restaurant>> = restaurantsLiveData

    init {
        viewModelScope.launch {
            val restaurants = api.getRestaurants()
            //JUST WNANT TO SEE THE PROGRESS BAR
            delay(2000)
            restaurantsLiveData.value = restaurants
        }
    }

}