package com.example.st10459664mastpart2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuViewModel : ViewModel() {

    // MutableLiveData for menu items
    private val _menuItems = MutableLiveData<MutableList<MenuItem>>()
    val menuItems: LiveData<MutableList<MenuItem>> = _menuItems

    init {
        _menuItems.value = mutableListOf() // Initialize with an empty list
    }

    // Add a new MenuItem to the list and update LiveData
    fun addMenuItem(menuItem: MenuItem) {
        val currentList = _menuItems.value ?: mutableListOf()
        currentList.add(menuItem)
        _menuItems.value = currentList
    }

    // Remove a MenuItem by index and update LiveData
    fun removeMenuItem(position: Int) {
        val currentList = _menuItems.value ?: mutableListOf()
        if (position >= 0 && position < currentList.size) {
            currentList.removeAt(position)
            _menuItems.value = currentList
        }
    }

    // Get average price by course and return a map of course to average price
    fun getAveragePrices(): Map<String, Double> {
        val courses = listOf("Starters", "Mains", "Desserts")
        return courses.associateWith { course ->
            val itemsInCourse = _menuItems.value?.filter { it.course == course }
            itemsInCourse?.map { it.price }?.average() ?: 0.0
        }
    }
}



