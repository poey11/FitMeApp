package com.example.mobdeve.s13.payao.malcolm.fitme.models

import java.io.Serializable

/*
*
* Will update as we continue to develop our app
* */
/*
class Workout(
    val workoutId: Int,
    val workoutTitle: String,
    val workoutDays: Array<String>,
    val circuit: Array<Circuit>
):Serializable
 */
class Workout(
    val workoutTitle: String = "",
    val workoutDays: Array<String> = emptyArray(),
    val circuit: Array<Circuit> = emptyArray()
): Serializable
