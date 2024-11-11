package com.example.st10459664mastpart2

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.st10459664mastpart2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MenuAdapter

    companion object {
        const val MENU_ITEM_KEY = "newMenuItem"
        val menuItems = mutableListOf<MenuItem>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        updateTotalMenuItems()
        displayAveragePriceByCourse()

        binding.btnAddItem.setOnClickListener {
            val intent = Intent(this, AddItemActivity::class.java)
            startForResult.launch(intent)
        }

        binding.btnFilter.setOnClickListener {
            val intent = Intent(this, FilterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewMenu.layoutManager = LinearLayoutManager(this)
        adapter = MenuAdapter(menuItems)
        binding.recyclerViewMenu.adapter = adapter
    }

    private fun calculateAveragePriceByCourse(): Map<String, Double> {
        val coursePrices = mutableMapOf<String, MutableList<Double>>()
        for (item in menuItems) {
            if (!coursePrices.containsKey(item.course)) {
                coursePrices[item.course] = mutableListOf()
            }
            coursePrices[item.course]?.add(item.price)
        }
        return coursePrices.mapValues { (_, prices) ->
            prices.sum() / prices.size
        }
    }

    private fun displayAveragePriceByCourse() {
        val averagePrices = calculateAveragePriceByCourse()
        binding.tvAveragePrices.text = averagePrices.entries.joinToString("\n") {
            "${it.key}: ${String.format("%.2f", it.value)}"
        }
    }

    private fun updateTotalMenuItems() {
        binding.tvTotalItems.text = "Total Menu Items: ${menuItems.size}"
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val newItem = result.data?.getParcelableExtra<MenuItem>(MENU_ITEM_KEY)
            newItem?.let {
                menuItems.add(it)
                updateTotalMenuItems()
                displayAveragePriceByCourse()
                adapter.notifyDataSetChanged()
            }
        }
    }
}









