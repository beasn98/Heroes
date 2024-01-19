package com.example.heroes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero(
    val name: String,
    val description: String,
    val superpower: String,
    val ranking: Int,
    val image: String
): Parcelable, Comparable<Hero> {
    override fun compareTo(other: Hero): Int {
        //order by ranking
        //if curr hero is rank 1
        // and other hero is rank 3
        // it would return negative -2
        return this.ranking - other.ranking
    }
}
