package com.example.heroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import com.example.heroes.databinding.ActivityHeroesDetailBinding

class HeroesDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeroesDetailBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hero = intent.getParcelableExtra<Hero>(HeroAdapter.EXTRA_HERO)
        val name = hero?.name
        val description = hero?.description
        val ranking = hero?.ranking.toString()
        val superpower = hero?.superpower
        val imageName = hero?.image

        binding.imageViewHeroesDetailImage.setImageDrawable(
            getDrawable(resources.getIdentifier(hero?.image, "drawable", packageName))
        )

        binding.textViewHeroesDetailName.setText(name)
        binding.textViewHeroesDetailRealDescription.setText(description)
        binding.textViewHeroesDetailRealRanking.setText(ranking)
        binding.textViewHeroesDetailRealSuperpower.setText(superpower)
    }
}