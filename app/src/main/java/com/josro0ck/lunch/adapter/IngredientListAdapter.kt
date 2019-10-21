package com.josro0ck.lunch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.josro0ck.lunch.R
import com.josro0ck.lunch.model.Ingredient

class IngredientListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var ingredients = emptyList<Ingredient>()

    inner class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ingredientItemView: TextView = itemView.findViewById(R.id.ingredientName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val itemView = inflater.inflate(R.layout.ingredient_item, parent, false)
        return IngredientViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val current = ingredients[position]
        holder.ingredientItemView.text = current.name
    }

    internal fun setIngredients(words: List<Ingredient>) {
        this.ingredients = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = ingredients.size
}