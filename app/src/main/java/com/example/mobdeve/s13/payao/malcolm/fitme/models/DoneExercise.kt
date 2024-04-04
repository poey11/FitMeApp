package com.example.mobdeve.s13.payao.malcolm.fitme.models

data class DoneExercise(
    val title: String,
    val reps: Int,
    val kg: Float,
    val sets: Int
) {
    override fun toString(): String {
        return "Title: $title, Sets: $sets, Reps: $reps, Kg: $kg "
    }
}
