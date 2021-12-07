package com.example.gamelb

import java.io.Serializable

data class Game(var id : Int, var slug: String, var name: String, var released: String
                , var tba: Boolean, var background_image: String, var rating: Double): Serializable {
}