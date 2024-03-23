package com.example.mobdeve.s13.payao.malcolm.fitme.database

import com.example.mobdeve.s13.payao.malcolm.fitme.models.Exercise

class ExerciseDataHelper {
    companion object {
        fun initializeExercises(): ArrayList<Exercise> {
            val exercises = ArrayList<Exercise>()

            exercises.add(
                Exercise(
                    1,
                    "Push-up",
                    reps = 10,
                    sets = 1,
                    KG= 0
                )
            )

            exercises.add(
                Exercise(
                    2,
                    "Pull-up",
                    reps = 8,
                    sets = 2,
                    KG = 10
                )
            )

            exercises.add(
                Exercise(
                    3,
                    "Squats",
                    reps = 12,
                    sets = 3,
                    KG = 5
                )
            )

            exercises.add(
                Exercise(
                    4,
                    "Crunches",
                    reps = 15,
                    sets = 4,
                    KG = 0
                )
            )

            return exercises
        }

    }
    fun getMaxKG(exercises: ArrayList<Exercise>): Int {
        var maxKG = Int.MIN_VALUE
        for (exercise in exercises) {
            if (exercise.KG > maxKG) {
                maxKG = exercise.KG
            }
        }
        return maxKG
    }
}
