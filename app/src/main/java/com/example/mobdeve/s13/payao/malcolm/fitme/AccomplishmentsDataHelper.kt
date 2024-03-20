package com.example.mobdeve.s13.payao.malcolm.fitme


class AccomplishmentsDataHelper {
    companion object {
        fun initializeAccomplishments(): ArrayList<Accomplishment> {
            val accomplishments = ArrayList<Accomplishment>()

            accomplishments.add(
                Accomplishment(
                    "Cossack Squat"
                )
            )

            accomplishments.add(
                Accomplishment(
                    "Step - Ups"
                )
            )

            accomplishments.add(
                Accomplishment(
                    "Bulgarian Split Squat"
                )
            )

            return accomplishments
        }
    }
}
