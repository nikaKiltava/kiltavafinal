package com.example.kiltavafinal.data.model

import java.io.Serializable

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val vote_average: Double
) : Serializable