package com.example.mobdeve.s13.payao.malcolm.fitme.database

import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.models.UserData



class UserDataHelper {
    companion object {
        fun initializeUserData(): ArrayList<UserData> {
            val users = ArrayList<UserData>()
            users.add(
                UserData(
                    "John",
                    "Derick",
                    31,
                    "45,018",
                    "5,594",
                    R.drawable.derick,
                    "john_derickkk@gmail.com",
                    "hehehehe",
                    20,
                    65f,
                    170f

                )
            )
            // Add more users as needed
            return users
        }
    }
}

