package com.example.cachingapplication.features.restaurants

import androidx.lifecycle.*
import com.example.cachingapplication.api.RestaurantApi
import com.example.cachingapplication.data.Restaurant
import com.example.cachingapplication.data.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    //api: RestaurantApi
    repository: RestaurantRepository
) : ViewModel() {

//    private val restaurantsLiveData = MutableLiveData<List<Restaurant>>()
//
//    //activity access -> just read, not change, only viewmodel can change
//    val restaurants: LiveData<List<Restaurant>> = restaurantsLiveData
//
//    init {
//        viewModelScope.launch {
//            val restaurants = api.getRestaurants()
//            //JUST WNANT TO SEE THE PROGRESS BAR
//            delay(2000)
//            restaurantsLiveData.value = restaurants
//        }
//    }

    val restaurants = repository.getRestaurants().asLiveData()
}