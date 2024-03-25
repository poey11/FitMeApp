package com.example.mobdeve.s13.payao.malcolm.fitme


class ScheduledExerciseDataHelper {
    companion object {
        fun initializeExercises(): ArrayList<ScheduledExercise> {
            val exercises = ArrayList<ScheduledExercise>()

            exercises.add(
                ScheduledExercise(
                    1,
                    "Push-up",
                    20

                    )
            )

            exercises.add(
                ScheduledExercise(
                    2,
                    "Pull-up",
                    10
                    )
            )

            exercises.add(
                ScheduledExercise(
                    3,
                    "Squats",
                    20
                    )
            )

            exercises.add(
                ScheduledExercise(
                    4,
                    "Crunches",
                    20
                )
            )


            return exercises
        }
    }
}