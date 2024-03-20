package com.example.mobdeve.s13.payao.malcolm.fitme.database

import com.example.mobdeve.s13.payao.malcolm.fitme.models.Workout

class DataHelper {
    companion object{
        fun initializeWorkout(): ArrayList<Workout>{
            val workout = ArrayList<Workout>()
            workout.add(
                Workout(
                    1,
                    "Bicep Workout",
                    arrayOf("M","T","W")
                )
            )
            workout.add(
                Workout(
                    2,
                    "Back Workout",
                    arrayOf("TH","F","SA")
                )
            )
            workout.add(
                Workout(
                    3,
                    "Chest Workout",
                    arrayOf("SU", "M", "W")
                )
            )
            workout.add(
                Workout(
                    4,
                    "Legs Workout",
                    arrayOf("M", "W", "F")
                )
            )
            workout.add(
                Workout(
                    5,
                    "Calves Workout",
                    arrayOf("T", "TH", "SA")
                )
            )
            workout.add(
                Workout(
                    6,
                    "Shoulders Workout",
                    arrayOf("SU", "W")
                )
            )
            return  workout
        }
    }
}