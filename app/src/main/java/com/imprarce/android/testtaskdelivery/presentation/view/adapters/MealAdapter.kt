package com.imprarce.android.testtaskdelivery.presentation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imprarce.android.testtaskdelivery.R
import com.imprarce.android.testtaskdelivery.data.model.MealItem
import com.squareup.picasso.Picasso

class MealAdapter(private val mealItem: List<MealItem>) : RecyclerView.Adapter<MealAdapter.MealViewHolder>(){


    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTextView: TextView = itemView.findViewById(R.id.pizza_name)
        val descriptionTextView: TextView = itemView.findViewById(R.id.pizza_text)
        val mealImageView: ImageView = itemView.findViewById(R.id.meal_image)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_meals_recycler_view, parent, false)
        return MealViewHolder(itemView)
    }

    override fun getItemCount(): Int = mealItem.size

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val currentMeal = mealItem[position]
        holder.titleTextView.text = currentMeal.title
        holder.descriptionTextView.text = currentMeal.ingredients
        Picasso.get().load(currentMeal.url).into(holder.mealImageView)
    }

}