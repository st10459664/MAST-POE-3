package com.example.st10459664mastpart2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(private var menuItems: List<MenuItem>) : RecyclerView.Adapter<MenuAdapter.MenuItemViewHolder>() {

    class MenuItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dishNameTextView: TextView = view.findViewById(R.id.dishNameTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        val courseTextView: TextView = view.findViewById(R.id.courseTextView)
        val priceTextView: TextView = view.findViewById(R.id.priceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.menu_item, parent, false)
        return MenuItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val menuItem = menuItems[position]
        holder.dishNameTextView.text = menuItem.name
        holder.descriptionTextView.text = menuItem.description
        holder.courseTextView.text = menuItem.course
        holder.priceTextView.text = "$${menuItem.price}"
    }

    override fun getItemCount(): Int = menuItems.size

    fun updateMenuItems(newItems: List<MenuItem>) {
        menuItems = newItems
        notifyDataSetChanged()
    }
}



