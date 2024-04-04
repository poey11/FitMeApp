package com.example.mobdeve.s13.payao.malcolm.fitme.models

data class DoneExercise(
    val exTitle: String,
    val reps: Int,
    val kg: Float,
    val sets: Int
) {
    override fun toString(): String {
        return "Title: $exTitle, Sets: $sets, Reps: $reps, Kg: $kg "
    }
}
