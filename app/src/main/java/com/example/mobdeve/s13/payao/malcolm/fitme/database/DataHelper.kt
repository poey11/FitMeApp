package com.example.mobdeve.s13.payao.malcolm.fitme.database

import com.example.mobdeve.s13.payao.malcolm.fitme.models.Workout

class DataHelper {
    companion object{
        fun initializeWorkout(): ArrayList<Workout>{
            val workout = ArrayList<Workout>()
            workout.add(
                Workout(
                    1,
                    "Bicep",
                    arrayOf("M","T","W")
                )
            )
            workout.add(
                Workout(
                    2,
                    "Back",
                    arrayOf("TH","F","SA")
                )
            )
            workout.add(
                Workout(
                    3,
                    "Chest",
                    arrayOf("SU", "M", "W")
                )
            )
            workout.add(
                Workout(
                    4,
                    "Legs",
                    arrayOf("M", "W", "F")
                )
            )
            workout.add(
                Workout(
                    5,
                    "Calves",
                    arrayOf("T", "TH", "SA")
                )
            )
            workout.add(
                Workout(
                    6,
                    "Shoulders",
                    arrayOf("SU", "W")
                )
            )
            workout.add(
                Workout(
                    7,
                    "Hamstrings",
                    arrayOf("SU", "W")
                )
            )
            return  workout
        }
    }
}