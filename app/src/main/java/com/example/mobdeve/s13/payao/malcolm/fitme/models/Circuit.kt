package com.example.mobdeve.s13.payao.malcolm.fitme.models

import java.io.Serializable

class Circuit(
    val setOfExercise: List<C_exercise>
):Serializable


/*
data class Circuit(
    val ExerciseTitle: String,
    val Reps: Int? = null, // Default to null if reps are not available
    val Sets: Int? = null // Default to null if sets are not available
)
*/