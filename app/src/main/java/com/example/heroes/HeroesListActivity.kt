package com.example.heroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heroes.databinding.ActivityHeroesListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HeroesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeroesListBinding
    private lateinit var heroesList: List<Hero>
    private lateinit var heroAdapter: HeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inputStream = resources.openRawResource(R.raw.heroes)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        val gson = Gson()
        val type = object : TypeToken<List<Hero>>() {}.type // data type of the list.
        val list = gson.fromJson<List<Hero>>(jsonString, type).sorted()
        heroesList = list
        // Link the adapter to the recyclerView
        refresh()
    }

    private fun refresh() {
        val adapter = HeroAdapter(heroesList)
        heroAdapter = adapter
        binding.recyclerViewHeroesListHeroes.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewHeroesListHeroes.adapter = adapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.herolist_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuItem_menu_sortName -> {
                heroesList = heroesList.sortedBy { it.name }
                refresh()
                Toast.makeText(this, "Sorted by name", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menuItem_menu_sortRank -> {
                heroesList = heroesList.sortedBy { it.ranking }
                refresh()
                Toast.makeText(this, "Sorted by rank", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menuItem_menu_sortDesc -> {
                heroesList = heroesList.sortedBy { it.description.length }
                refresh()
                Toast.makeText(this, "Sorted by description length", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}