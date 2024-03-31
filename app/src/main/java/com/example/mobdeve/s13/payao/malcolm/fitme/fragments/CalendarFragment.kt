package com.example.mobdeve.s13.payao.malcolm.fitme


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*
import kotlin.collections.ArrayList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.ScheduledExerciseAdapter
import com.example.mobdeve.s13.payao.malcolm.fitme.models.ScheduledExercise
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import com.example.mobdeve.s13.payao.malcolm.fitme.activities.ViewWorkout


class CalendarFragment : Fragment(), CalendarAdapter.OnItemListener, ScheduledExerciseAdapter.OnWorkoutTitleClickListener {

    private lateinit var monthYearText: TextView
    private lateinit var calendarRecyclerView: RecyclerView
    private lateinit var selectedDate: Calendar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        initWidgets(view)
        selectedDate = Calendar.getInstance()
        setMonthView()

        return view
    }

    private fun initWidgets(view: View) {
        calendarRecyclerView = view.findViewById(R.id.calendarRecyclerView)
        monthYearText = view.findViewById(R.id.monthYearTV)
        val previousButton = view.findViewById<Button>(R.id.previousbtn)
        val nextButton = view.findViewById<Button>(R.id.nextbtn)

        previousButton.setOnClickListener {
            previousMonthAction()
        }

        nextButton.setOnClickListener {
            nextMonthAction()
        }
    }


    private fun setMonthView() {
        monthYearText.text = monthYearFromDate(selectedDate)
        val daysInMonth = daysInMonthArray(selectedDate)
        val currentMonth = selectedDate.get(Calendar.MONTH)
        val currentYear = selectedDate.get(Calendar.YEAR)

        val calendarAdapter = CalendarAdapter(daysInMonth, this, currentMonth, currentYear)
        val layoutManager = GridLayoutManager(requireContext(), 7)
        calendarRecyclerView.layoutManager = layoutManager
        calendarRecyclerView.adapter = calendarAdapter
    }


    private fun daysInMonthArray(date: Calendar): ArrayList<String> {
        val daysInMonthArray = ArrayList<String>()
        val year = date.get(Calendar.YEAR)
        val month = date.get(Calendar.MONTH)
        val maxDaysOfMonth = date.getActualMaximum(Calendar.DAY_OF_MONTH)
        val firstDayOfMonth = Calendar.getInstance()
        firstDayOfMonth.set(year, month, 1)
        val firstDayOfWeek = firstDayOfMonth.get(Calendar.DAY_OF_WEEK)

        for (i in 1 until firstDayOfWeek) {
            daysInMonthArray.add("")
        }

        for (day in 1..maxDaysOfMonth) {
            daysInMonthArray.add(day.toString())
        }

        while (daysInMonthArray.size % 7 != 0) {
            daysInMonthArray.add("")
        }

        return daysInMonthArray
    }

    private fun monthYearFromDate(date: Calendar): String {
        val month = date.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault())
        val year = date.get(Calendar.YEAR)
        return "$month $year"
    }

    private fun previousMonthAction() {
        selectedDate.add(Calendar.MONTH, -1)
        setMonthView()
    }

    private fun nextMonthAction() {
        selectedDate.add(Calendar.MONTH, 1)
        setMonthView()
    }

    override fun onItemClick(position: Int, dayText: String?) {
        if (!dayText.isNullOrEmpty()) {
            val currentUser = FirebaseAuth.getInstance().currentUser
            val uid = currentUser?.uid
            uid?.let {
                val dialog = MyBottomSheetDialogFragment.newInstance(dayText, it, this)
                dialog.show(childFragmentManager, "MyBottomSheetDialogFragment")
                dialog.fetchWorkouts(calendarRecyclerView.adapter as? ScheduledExerciseAdapter ?: return@let, it)
            }
        }
    }

    // Handle click event for workout titles
    override fun onWorkoutTitleClick(workoutTitle: String, workoutID:String) {
        // Start ViewWorkout activity with the workout title
        val intent = Intent(requireContext(), ViewWorkout::class.java)
        intent.putExtra("clickedWorkoutTitle", workoutTitle)
        intent.putExtra("clickedWorkoutID", workoutID)
        startActivity(intent)
    }



    class MyBottomSheetDialogFragment(private val listener: ScheduledExerciseAdapter.OnWorkoutTitleClickListener) : BottomSheetDialogFragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.calendar_bottomsheet, container, false)

            // Retrieve UID from arguments
            val uid = arguments?.getString("uid") ?: ""

            // Initialize the RecyclerView
            val recyclerView = view.findViewById<RecyclerView>(R.id.scheduledExercises)

            // Set the layout manager for your RecyclerView
            val layoutManager = LinearLayoutManager(requireContext())
            recyclerView.layoutManager = layoutManager

            // Initialize the adapter with an empty list and pass the listener
            val adapter = ScheduledExerciseAdapter(ArrayList(), listener)

            // Set the adapter to your RecyclerView
            recyclerView.adapter = adapter

            // Fetch exercises from the database using the UID
            fetchWorkouts(adapter, uid)

            return view
        }


        fun fetchWorkouts(adapter: ScheduledExerciseAdapter, uid: String) {
            val uid = arguments?.getString("uid") // Retrieve the uid from arguments
            uid?.let {
                // Access your Firebase Firestore instance
                val db = FirebaseFirestore.getInstance()

                // Fetch exercises from Firestore
                db.collection("userExercise").document(it)
                    .collection("listOfWorkouts")
                    .get()
                    .addOnSuccessListener { result ->
                        val exercises = mutableListOf<ScheduledExercise>()

                        // Iterate through the result documents
                        for (document in result) {
                            // Extract workout title
                            val workoutTitle = document.getString("workoutTitle")
                            workoutTitle?.let {
                                // Create a ScheduledExercise object with the workout title
                                val scheduledExercise = ScheduledExercise(it, document.id)
                                exercises.add(scheduledExercise)
                            }
                        }

                        // Update the adapter with the fetched exercises
                        adapter.setData(ArrayList(exercises))
                    }
                    .addOnFailureListener { exception ->
                        // Handle errors
                        Log.e("MyBottomSheet", "Error fetching workouts: $exception")
                    }
            }
        }



        companion object {
            fun newInstance(dayText: String, uid: String, listener: ScheduledExerciseAdapter.OnWorkoutTitleClickListener): MyBottomSheetDialogFragment {
                val fragment = MyBottomSheetDialogFragment(listener)
                val args = Bundle()
                args.putString("selectedDay", dayText)
                args.putString("uid", uid) // Pass the UID as an argument
                fragment.arguments = args
                return fragment
            }
        }
    }
}





