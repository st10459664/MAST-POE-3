package com.example.st10459664mastpart2

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        val etDishName = findViewById<EditText>(R.id.etDishName)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val spinnerCourse = findViewById<Spinner>(R.id.spinnerCourse)
        val etPrice = findViewById<EditText>(R.id.etPrice)

        val courses = arrayOf("Starters", "Mains", "Desserts")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, courses)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCourse.adapter = adapter

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val name = etDishName.text.toString().trim()
            val description = etDescription.text.toString().trim()
            val course = spinnerCourse.selectedItem.toString()
            val priceString = etPrice.text.toString().trim()

            if (name.isEmpty() || description.isEmpty() || priceString.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields correctly", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val price = priceString.toDoubleOrNull()
            if (price == null || price <= 0) {
                Toast.makeText(this, "Please enter a valid price greater than 0", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newMenuItem = MenuItem(name, description, course, price)
            val resultIntent = Intent()
            resultIntent.putExtra(MainActivity.MENU_ITEM_KEY, newMenuItem)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}






