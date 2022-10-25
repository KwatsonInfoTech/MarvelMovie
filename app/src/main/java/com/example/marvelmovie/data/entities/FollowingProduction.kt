package com.example.marvelmovie.data.entities

data class FollowingProduction(
    val days_until: Int,
    val overview: String,
    val poster_url: String,
    val release_date: String,
    val title: String,
    val type: String
)