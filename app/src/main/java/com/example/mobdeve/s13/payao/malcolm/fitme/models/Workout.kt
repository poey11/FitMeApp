package com.example.mobdeve.s13.payao.malcolm.fitme.models

import java.io.Serializable

class Workout(
    val workoutTitle: String = "",
    val workoutDays: Array<String> = emptyArray(),
    val circuit: Array<Circuit> = emptyArray(),
    val workoutID: String
): Serializable
