package com.example.cachingapplication.data

import androidx.room.withTransaction
import com.example.cachingapplication.api.RestaurantApi
import com.example.cachingapplication.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val api: RestaurantApi,
    private val db: RestaurantDatabase
) {
    private val restaurantDao = db.restaurantDao()

    fun getRestaurants() = networkBoundResource(
        query = {
            restaurantDao.getAllRestaurants()
        },
        fetch = {
            delay(2000)
            api.getRestaurants()
        },
        saveFetchedResult = {restaurants ->
            db.withTransaction {
                restaurantDao.deleteAllRestaurants()
                restaurantDao.insertRestaurants(restaurants)
            }
        }
    )
}