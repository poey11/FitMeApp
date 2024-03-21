package com.example.mobdeve.s13.payao.malcolm.fitme.database

import com.example.mobdeve.s13.payao.malcolm.fitme.Exercise

class ExerciseDataHelper {
    companion object {
        fun initializeExercises(): ArrayList<Exercise> {
            val exercises = ArrayList<Exercise>()

            exercises.add(
                Exercise(
                    1,
                    "Push-up",

                )
            )

            exercises.add(
                Exercise(
                    2,
                    "Pull-up",

                )
            )

            exercises.add(
                Exercise(
                    3,
                    "Squats",

                )
            )

            exercises.add(
                Exercise(
                    4,
                    "Crunches",
                )
            )

            return exercises
        }
    }
}
