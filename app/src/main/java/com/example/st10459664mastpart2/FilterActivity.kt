package com.example.st10459664mastpart2

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class FilterActivity : AppCompatActivity() {

    private val menuViewModel: MenuViewModel by viewModels()  // Access ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val courseSpinner: Spinner = findViewById(R.id.spinnerCourse)
        val listViewFiltered: ListView = findViewById(R.id.listViewFiltered)
        val tvEmptyMessage: TextView = findViewById(R.id.tvEmptyMessage)  // TextView for empty list message

        val courses = arrayOf("All", "Starters", "Mains", "Desserts")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, courses)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        courseSpinner.adapter = adapter

        courseSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedCourse = parent.getItemAtPosition(position) as String
                menuViewModel.menuItems.observe(this@FilterActivity, Observer { menuItems ->
                    val filteredItems = if (selectedCourse == "All") {
                        menuItems
                    } else {
                        menuItems.filter { it.course == selectedCourse }
                    }

                    if (filteredItems.isEmpty()) {
                        tvEmptyMessage.visibility = View.VISIBLE  // Show message if no items are found
                        listViewFiltered.visibility = View.GONE
                    } else {
                        tvEmptyMessage.visibility = View.GONE
                        listViewFiltered.visibility = View.VISIBLE
                        val filteredAdapter = ArrayAdapter(this@FilterActivity, android.R.layout.simple_list_item_1,
                            filteredItems.map { "${it.name} - $${it.price}" })
                        listViewFiltered.adapter = filteredAdapter
                    }
                })
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
}

