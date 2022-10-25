package com.example.marvelmovie.data.entities

import androidx.room.PrimaryKey

data class Movie(
    @PrimaryKey
    val title: String,
    val days_until: Int,
    val following_production: FollowingProduction,
    val overview: String,
    val poster_url: String,
    val release_date: String,
    val type: String
)