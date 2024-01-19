package com.example.heroes

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class HeroAdapter(var heroesList: List<Hero>) :
    RecyclerView.Adapter<HeroAdapter.ViewHolder>() {


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewRanking: TextView
        val textViewName: TextView
        val textViewDescrption: TextView
        val layout: ConstraintLayout

        init {
            // Define click listener for the ViewHolder's View
            textViewRanking = view.findViewById(R.id.textView_heroItem_ranking)
            textViewName = view.findViewById(R.id.textView_heroItem_name)
            textViewDescrption = view.findViewById(R.id.textView_heroItem_description)
            layout = view.findViewById(R.id.layout_itemHero)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_hero, viewGroup, false)

        return ViewHolder(view)
    }

    companion object {
        val EXTRA_HERO = "hero"
    }


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val hero = heroesList[position]
        val context = viewHolder.layout.context
        viewHolder.textViewRanking.text = hero.ranking.toString()
        viewHolder.textViewName.text = hero.name
        viewHolder.textViewDescrption.text = hero.description

        // click listener for clicking anywhere on the current hero
        viewHolder.layout.setOnClickListener{
            val heroIntent = Intent(context, HeroesDetailActivity::class.java)
            heroIntent.putExtra(EXTRA_HERO, hero)
            context.startActivity(heroIntent)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = heroesList.size

}